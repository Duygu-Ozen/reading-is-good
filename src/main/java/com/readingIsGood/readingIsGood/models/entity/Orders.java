package com.readingIsGood.readingIsGood.models.entity;

import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDetailsDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customerId;

    private String state;

    private Date createDate;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    private List<OrderDetails> orderDetails;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public OrderDTO toDTO(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(customerId.toDTO());
        orderDTO.setId(id);
        orderDTO.setState(state);
        orderDTO.setCreateDate(createDate);

        List<OrderDetailsDTO> orderDetailsDTOList = orderDetails.stream().map(OrderDetails::toDTO).collect(Collectors.toList());
        orderDTO.setOrderDetails(orderDetailsDTOList);

        return orderDTO;
    }
}
