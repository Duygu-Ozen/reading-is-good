package com.readingIsGood.readingIsGood.api.controller;


import com.readingIsGood.readingIsGood.models.dto.*;
import com.readingIsGood.readingIsGood.service.IStatisticsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticControllerTest {
    @Mock
    private IStatisticsService iStatisticsService;

    @InjectMocks
    private StatisticsController statisticsController;

    @Before
    public void init() {

    }


    @Test
    public void testGetStatisticValues() {
        OrderStatisticDTO orderStatisticDTO =new OrderStatisticDTO();
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

        List<OrderDetailsDTO> orderDetailDtotList=new ArrayList<OrderDetailsDTO>();
        OrderDetailsDTO orderDetailsDTO =new OrderDetailsDTO();
        BookDTO bookDTO =new BookDTO();

        bookDTO.setQuantity(5);
        bookDTO.setTitle("denemeKitap");
        bookDTO.setIsbn("123456");

        orderDetailsDTO.setQuantity(5);
        orderDetailsDTO.setAmount(BigDecimal.valueOf(1233.00));
        orderDetailsDTO.setBook(bookDTO);
        orderDetailDtotList.add(orderDetailsDTO);

        orderDTO.setOrderDetails(orderDetailDtotList);

        when(iStatisticsService.getOrderStatisticValues(customerDTO.getId()))
                .thenReturn(Arrays.asList(orderStatisticDTO));

        List <OrderStatisticDTO> orderStatisticDTOReal = statisticsController.getStatisticValues(customerDTO.getId());

        Assert.assertEquals(orderStatisticDTOReal.get(0).getTotalOrderCount(),orderStatisticDTO.getTotalOrderCount());
    }



}
