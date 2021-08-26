package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.CustomerRequest;
import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;

import java.util.List;

public interface ICustomerService {

    public List<OrderDTO> getAllOrdersOfCustomer(Integer customerNumber, Integer pageNo, Integer pageSize);

    public CustomerDTO saveCustomer(CustomerRequest request);

}
