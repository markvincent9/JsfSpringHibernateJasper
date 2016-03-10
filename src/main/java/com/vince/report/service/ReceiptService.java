package com.vince.report.service;

import com.vince.model.CartItem;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReceiptService {

    public List<Map<String, ?>> getTransactionItemsMap(List<CartItem> cartItems){
        List<Map<String, ?>> receiptModel = new ArrayList<>();
        for(CartItem cartItem: cartItems){
            Map<String, Object> m = new HashMap<>();
            m.put("bookTitle", cartItem.getBook().getBookTitle());
            m.put("bookPrice", cartItem.getBook().getPrice().toString());
            m.put("quantity", cartItem.getQuantity().toString());
            m.put("total", String.valueOf(cartItem.getQuantity() * cartItem.getBook().getPrice()));
            receiptModel.add(m);
        }

        return receiptModel;
    }
}
