package com.vince.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction", schema = "public", catalog = "jsfdb")
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
