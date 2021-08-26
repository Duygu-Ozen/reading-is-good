package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.request.OrderRequest;
import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.service.IOrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private IOrderService iOrderService;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void init() {

    }

    @Test
    public void testCreate() {

        OrderRequest request = mock(OrderRequest.class);

        OrderDTO mockResult = new OrderDTO();
        mockResult.setId(123456);

        Mockito.when(iOrderService.saveOrder(request)).thenReturn(mockResult);

        Assert.assertEquals(iOrderService.saveOrder(request).getId(), mockResult.getId());

    }

    @Test
    public void testOrdersByDateInterval() throws ParseException {

        Date startDate=new SimpleDateFormat("yyyy-MM-dd").parse("2021-01-01");
        Date endDate=new SimpleDateFormat("yyyy-MM-dd").parse("2021-12-01");
        Date testDate=new SimpleDateFormat("yyyy-MM-dd").parse("2021-06-01");

        OrderDTO orderDTO = new OrderDTO();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(123456);
        customerDTO.setFirstName("duygu");
        customerDTO.setLastName("özen");
        customerDTO.setPhoneNumber("123456");
        customerDTO.setStreet("denemeSokak");
        customerDTO.setEmailAdress("deneme@gmail.com");

        orderDTO.setId(123456);
        orderDTO.setState("awaiting");
        orderDTO.setCustomer(customerDTO);
        orderDTO.setCreateDate(testDate);

        when(iOrderService.getOrderByDateInterval(startDate,endDate))
                .thenReturn(Arrays.asList(orderDTO));

        List<OrderDTO> ordersByDateInterval = orderController.getOrdersByDateInterval(startDate,endDate);

        Assert.assertEquals(ordersByDateInterval.get(0).getId(), orderDTO.getId());
        Assert.assertEquals(ordersByDateInterval.get(0).getCustomer().getId(), orderDTO.getCustomer().getId());

    }

    @Test
    public void testOrdersByCustomerId() {

        OrderDTO orderDTO = new OrderDTO();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(123456);
        customerDTO.setFirstName("duygu");
        customerDTO.setLastName("özen");
        customerDTO.setPhoneNumber("123456");
        customerDTO.setStreet("denemeSokak");
        customerDTO.setEmailAdress("deneme@gmail.com");

        orderDTO.setId(123456);
        orderDTO.setState("awaiting");
        orderDTO.setCustomer(customerDTO);
        orderDTO.setCreateDate(new Date());

        when(iOrderService.getOrderById(customerDTO.getId()))
                .thenReturn(Arrays.asList(orderDTO));

        List<OrderDTO> ordersByCustomerID = orderController.getOrdersById(customerDTO.getId());

        Assert.assertEquals(ordersByCustomerID.get(0).getId(), orderDTO.getId());
        Assert.assertEquals(ordersByCustomerID.get(0).getCustomer().getId(), orderDTO.getCustomer().getId());

    }

}
