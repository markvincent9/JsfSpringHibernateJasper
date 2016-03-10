package com.vince.entity;

import javax.persistence.*;

@Entity
@Table(name = "transaction_items", schema = "public", catalog = "jsfdb")
public class TransactionItems {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "item_count")
    private Integer itemCount;

    @Column(name = "book_id")
    private Integer bookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
