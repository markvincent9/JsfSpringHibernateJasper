package com.vince.controller;

import com.vince.dao.UserDao;
import com.vince.entity.User;
import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean(name="loginBean", eager = true)
@Component
@SessionScoped
public class LoginBean {

    @Resource
    private UserDao userDao;

    @Resource
    private NavigationBean navigationBean;

    @Resource
    private CartBean cartBean;

    private User user;

    private Boolean loginMode = false;

    private String username;

    private String password;

    private String columnSpan = "12";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(Boolean loginMode) {
        this.loginMode = loginMode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setColumnSpan(String columnSpan) {
        this.columnSpan = columnSpan;
    }

    public String getColumnSpan(){
        if(loginMode){
            return "8";
        }else{
            return "12";
        }
    }

    public String signUpUser(){
        navigationBean.setContent("faces/register.xhtml");
        return "index.xhtml";
    }

    public void login() {
        RequestContext context = RequestContext.getCurrentInstance();
        User user = userDao.findByUserNameAndPassword(username,password);
        if(user!=null){
            this.user = user;
            setLoginMode(true);
            navigationBean.setContent("/faces/books.xhtml");
            context.update("bookstoreContent");
            context.update("bookstoreNavbar");
            context.addCallbackParam("loggedIn", loginMode);
        }else{

            setLoginMode(false);
            context.addCallbackParam("loggedIn", loginMode);
        }
    }

    public void logout(){
        RequestContext context = RequestContext.getCurrentInstance();
        setUser(null);
        setLoginMode(false);
        cartBean.setPurchaseView(false);
        cartBean.setCartItemList(new ArrayList<>());
        navigationBean.setContent("faces/books.xhtml");
        context.update("bookstoreContent");
        context.update("bookstoreNavbar");

    }


}
