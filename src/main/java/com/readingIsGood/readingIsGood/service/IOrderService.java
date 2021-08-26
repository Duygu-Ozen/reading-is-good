package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.OrderRequest;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;

import java.util.Date;
import java.util.List;

public interface IOrderService {

    public List<OrderDTO> getOrderByDateInterval(Date startDate, Date endDate);

    public OrderDTO saveOrder(OrderRequest request);

    public List<OrderDTO> getOrderById(Integer customerId);


}
