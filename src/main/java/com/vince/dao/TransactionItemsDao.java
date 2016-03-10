package com.vince.dao;

import com.vince.entity.TransactionItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemsDao extends JpaRepository<TransactionItems,Integer> {
}
