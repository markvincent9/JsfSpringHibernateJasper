package com.vince.controller;

import com.vince.entity.Book;
import com.vince.model.CartItem;
import com.vince.report.service.ReceiptService;
import com.vince.service.PurchaseService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@Component
@SessionScoped
public class CartBean {

    @Resource
    private NavigationBean navigationBean;

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private LoginBean loginBean;

    @Resource
    private ReceiptService receiptService;

    private List<CartItem> cartItemList = new ArrayList<>();

    private Boolean purchaseView = false;

    private Integer currentTransactionId;

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Boolean getPurchaseView() {
        return purchaseView;
    }

    public void setPurchaseView(Boolean purchaseView) {
        this.purchaseView = purchaseView;
    }

    public void addToCart(Book book){
        if(!ifCartContainsBook(cartItemList, book.getId())){
            cartItemList.add(new CartItem(book, 1));
            System.out.println("Book " + book.getBookTitle() + " was added");
        }
    }

    public void lessQuantity(CartItem cartItem){
        cartItem.setQuantity(cartItem.getQuantity() - 1);
    }

    public void addQuantityOfItem(CartItem cartItem){
        cartItem.setQuantity(cartItem.getQuantity() + 1);
    }

    public void removeToCart(CartItem cartItem){
        cartItemList.remove(cartItem);
    }

    public boolean ifCartContainsBook(List<CartItem> cartItems, Integer bookId){
        return cartItems.stream()
                .filter(cItem -> cItem.getBook().getId().equals(bookId)).findFirst().isPresent();

    }

    public Double computeItemTotal(CartItem cartItem){
        return cartItem.getBook().getPrice() * cartItem.getQuantity();
    }

    public Double computeCartTotal(){
        Double total = 0.00;

        for(CartItem cartItem: cartItemList){
            total += computeItemTotal(cartItem);
        }

        return total;
    }

    public void purchaseView(){
        setPurchaseView(true);
        navigationBean.changeContent("/faces/purchase.xhtml");
        RequestContext.getCurrentInstance().update("bookstoreContent");
    }

    public void purchase(){
        currentTransactionId = purchaseService.purchase(cartItemList, loginBean.getUser());
        navigationBean.changeContent("/faces/receipt.xhtml");
        RequestContext.getCurrentInstance().update("bookstoreContent");
    }

    public void showReport(){
        try{
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(receiptService.getTransactionItemsMap(cartItemList));
            String jrxmlFile = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/receipt.jrxml");

            Map reportParemeters = new HashMap<>();
            reportParemeters.put("transactionId", currentTransactionId.toString());
            reportParemeters.put("transactionTotal", computeCartTotal().toString());

            InputStream inputStream = new FileInputStream(new File(jrxmlFile));
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, reportParemeters, jrDataSource);
            HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
                    .getExternalContext().getResponse();
            httpServletResponse.addHeader("Content-Type","application/pdf");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            FacesContext.getCurrentInstance().responseComplete();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
