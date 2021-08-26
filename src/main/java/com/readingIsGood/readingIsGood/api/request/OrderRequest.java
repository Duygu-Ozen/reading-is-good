package com.readingIsGood.readingIsGood.api.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class OrderRequest {

    @NotNull
    private Integer customerId;


    private String state;

    @Size(min = 1, message = "Order detail size must be at least 1")
    private List<OrderDetailRequest> orderDetailsRequest;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderDetailRequest> getOrderDetailsRequest() {
        return orderDetailsRequest;
    }

    public void setOrderDetailsRequest(List<OrderDetailRequest> orderDetailsRequest) {
        this.orderDetailsRequest = orderDetailsRequest;
    }
}
