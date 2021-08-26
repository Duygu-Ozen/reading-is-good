package com.readingIsGood.readingIsGood.models.entity;


import com.readingIsGood.readingIsGood.models.dto.OrderDetailsDTO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_details")
public class OrderDetails {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "isbn")
    private Books bookId;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id")
    private Orders orderId;

    private BigDecimal amount;

    private Integer quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
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

    public OrderDetailsDTO toDTO(){

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

        orderDetailsDTO.setBook(bookId.toDTO());
        orderDetailsDTO.setAmount(amount);
        orderDetailsDTO.setId(id);
        orderDetailsDTO.setQuantity(quantity);

        return orderDetailsDTO;
    }
}
