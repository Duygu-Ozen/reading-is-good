package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.request.CustomerRequest;
import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.service.ICustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
    @Mock
    ICustomerService iCustomerService;

    @InjectMocks
    CustomerController customerController;

    @Before
    public void init() {

    }

    @Test
    public void testCreate() {

        CustomerRequest request = mock(CustomerRequest.class);
        CustomerDTO mockResult = new CustomerDTO();
        mockResult.setId(123456);

        Mockito.when(iCustomerService.saveCustomer(request)).thenReturn(mockResult);
        Assert.assertEquals(iCustomerService.saveCustomer(request).getId(), mockResult.getId());

    }

    @Test
    public void testOrdersOfCustomerList() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(123456);
        customerDTO.setFirstName("duygu");
        customerDTO.setLastName("özen");
        customerDTO.setPhoneNumber("123456");
        customerDTO.setStreet("denemeSokak");
        customerDTO.setEmailAdress("deneme@gmail.com");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(customerDTO);
        orderDTO.setId(1);
        orderDTO.setCreateDate(new Date());

        when(iCustomerService.getAllOrdersOfCustomer(123, 0, 25))
                .thenReturn(Arrays.asList(orderDTO));

        List<OrderDTO> ordersOfCustomer = customerController.getOrdersOfCustomer(123, 25, 0);

        Assert.assertEquals(ordersOfCustomer.get(0).getId(), orderDTO.getId());
        Assert.assertEquals(ordersOfCustomer.get(0).getCustomer().getId(), orderDTO.getCustomer().getId());

    }

}
