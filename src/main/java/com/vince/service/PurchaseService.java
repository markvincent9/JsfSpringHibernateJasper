package com.vince.service;

import com.vince.dao.BookDao;
import com.vince.dao.TransactionDao;
import com.vince.dao.TransactionItemsDao;
import com.vince.entity.Book;
import com.vince.entity.Transaction;
import com.vince.entity.TransactionItems;
import com.vince.entity.User;
import com.vince.model.CartItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseService {

    @Resource
    private TransactionDao transactionDao;

    @Resource
    private TransactionItemsDao transactionItemsDao;

    @Resource
    private BookDao bookDao;

    @Transactional
    public Integer purchase(List<CartItem> cart, User user){

        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction = transactionDao.save(transaction);

        for(CartItem cartItem: cart){
            Book bookToUpdate = cartItem.getBook();
            bookToUpdate.setStock(bookToUpdate.getStock() - cartItem.getQuantity());
            bookDao.save(bookToUpdate);

            TransactionItems transactionItems = new TransactionItems();
            transactionItems.setBookId(bookToUpdate.getId());
            transactionItems.setItemCount(cartItem.getQuantity());
            transactionItems.setTransactionId(transaction.getId());
            transactionItemsDao.save(transactionItems);

        }

        return transaction.getId();

    }

}
