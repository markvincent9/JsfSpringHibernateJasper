package com.vince.controller;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@Component
@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean {

    // default content page
    private String content = "/faces/books.xhtml";

    public String getContent() {
        return content;
    }

    public void setContent(String page) {
        this.content = page;
    }

    public void changeContent(String page){
        setContent(page);
    }

    public void goToHome(){
        setContent("/faces/books.xhtml");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("bookstoreContent");
    }

}
