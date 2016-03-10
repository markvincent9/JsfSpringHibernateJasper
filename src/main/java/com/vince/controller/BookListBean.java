package com.vince.controller;

import com.vince.dao.BookDao;
import com.vince.entity.Book;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "bookListBean")
@Component
@SessionScoped
public class BookListBean {

    @Resource
    private BookDao bookDao;

    @Resource
    private CartBean cartBean;

    private List<Book> bookList = new ArrayList<Book>();

    public List<Book> getBookList() {
        return bookDao.findAll();
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public boolean disableAddToCart(Book book){
        return false;
    }
}
