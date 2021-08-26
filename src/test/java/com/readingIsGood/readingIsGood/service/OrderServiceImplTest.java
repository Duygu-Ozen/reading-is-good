package com.readingIsGood.readingIsGood.service;


import com.readingIsGood.readingIsGood.api.request.OrderDetailRequest;
import com.readingIsGood.readingIsGood.api.request.OrderRequest;
import com.readingIsGood.readingIsGood.repository.CustomerRepository;
import com.readingIsGood.readingIsGood.repository.OrdersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    OrdersRepository ordersRepository;

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @InjectMocks
    CustomerServiceImpl customerService;

    private OrderRequest request;

    @Before
    public void init() {

        request = new OrderRequest();
        List<OrderDetailRequest> orderDetailRequestList=new ArrayList<OrderDetailRequest>();
        OrderDetailRequest orderDetailRequest =new OrderDetailRequest();

        orderDetailRequest.setQuantity(5);
        orderDetailRequest.setAmount(BigDecimal.valueOf(1233.00));
        orderDetailRequest.setBookId("123455");
        orderDetailRequestList.add(orderDetailRequest);

        request.setState("awaiting");
        request.setCustomerId(123456);
        request.setOrderDetailsRequest(orderDetailRequestList);


    }

    @Test(expected = NoSuchElementException.class)
    public void testSaveOrderWhenCustomerIsNotExist() {

        Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.empty());
        orderService.saveOrder(request);

    }

}
