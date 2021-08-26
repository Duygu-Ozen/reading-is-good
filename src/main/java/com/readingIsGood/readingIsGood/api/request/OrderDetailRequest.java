package com.readingIsGood.readingIsGood.api.request;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class OrderDetailRequest {

    private String bookId;
    private BigDecimal amount;

    @Min(1)
    private Integer quantity;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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
