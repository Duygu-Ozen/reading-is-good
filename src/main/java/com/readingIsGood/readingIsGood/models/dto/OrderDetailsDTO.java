package com.readingIsGood.readingIsGood.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetailsDTO implements Serializable {

    private static final long serialVersionUID = 3734810910189240027L;

    private int id;
    private BookDTO book;
    private BigDecimal amount;
    private Integer quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
