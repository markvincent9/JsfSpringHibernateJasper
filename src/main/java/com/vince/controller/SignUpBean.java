package com.vince.controller;

import com.vince.dao.UserDao;
import com.vince.entity.User;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "signUpBean", eager = true)
@Component
@SessionScoped
public class SignUpBean {

    @Resource
    private UserDao userDao;

    @Resource
    private NavigationBean navigationBean;

    @Resource
    private LoginBean loginBean;

    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String confirmEmail;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public String register(){

        if(password.equals(confirmPassword) && email.equals(confirmEmail)){
            User user = userDao.save(new User(username , password, email));
            navigationBean.setContent("faces/books.xhtml");
            return "index.xhtml?faces-redirect=true";
        }

        return null;
    }
}
