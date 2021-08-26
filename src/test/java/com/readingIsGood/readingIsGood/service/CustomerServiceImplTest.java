package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.CustomerRequest;
import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;
import com.readingIsGood.readingIsGood.models.entity.Customer;
import com.readingIsGood.readingIsGood.models.entity.OrderDetails;
import com.readingIsGood.readingIsGood.models.entity.Orders;
import com.readingIsGood.readingIsGood.repository.CustomerRepository;
import com.readingIsGood.readingIsGood.repository.OrdersRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    OrdersRepository ordersRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    private CustomerRequest request;

    @Before
    public void init() {

        request = new CustomerRequest();
        request.setFirstName("duygu");
        request.setLastName("özen");
        request.setPhoneNumber("123456");
        request.setStreet("denemeSokak");
        request.setEmailAdress("deneme@gmail.com");
    }


    @Test(expected = IllegalArgumentException.class)
    public void testSaveCustomerWhenBookIsExist() {

        Optional<Customer> customer = Optional.of(mock(Customer.class));
        Mockito.when(customerRepository.findByEmailAdress(Mockito.any())).thenReturn(customer);

        customerService.saveCustomer(request);

    }

    @Test
    public void testSaveCustomerWhenBookIsNotExist() {

        Customer customer = new Customer();
        customer.setFirstName("duygu");
        customer.setLastName("özen");
        customer.setPhoneNumber("123456");
        customer.setStreet("denemeSokak");
        customer.setEmailAdress("deneme@gmail.com");

        Mockito.when(customerRepository.findByEmailAdress(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(customer);

        CustomerDTO customerDTO = customerService.saveCustomer(request);

        Assert.assertEquals(customerDTO.getEmailAdress(), request.getEmailAdress());
        Assert.assertEquals(customerDTO.getFirstName(), request.getFirstName());
        Assert.assertEquals(customerDTO.getLastName(), request.getLastName());
        Assert.assertEquals(customerDTO.getPhoneNumber(),request.getPhoneNumber());
        Assert.assertTrue(customerDTO.getStreet() == request.getStreet());
    }


    @Test(expected = NoSuchElementException.class)
    public void testOrdersOfCustomerWhenCustomerIsNotExist() {

        //Mockito.when(customerRepository.findByEmailAdress(Mockito.any())).thenReturn(Optional.empty());
        customerService.getAllOrdersOfCustomer(123,0,25);

    }

    @Test
    public void testOrdersOfCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(123456);
        customerDTO.setFirstName("duygu");
        customerDTO.setLastName("özen");
        customerDTO.setPhoneNumber("123456");
        customerDTO.setStreet("denemeSokak");
        customerDTO.setEmailAdress("deneme@gmail.com");

        Customer customer = new Customer();
        customer.setId(123456);
        customer.setFirstName("duygu");
        customer.setLastName("özen");
        customer.setPhoneNumber("123456");
        customer.setStreet("denemeSokak");
        customer.setEmailAdress("deneme@gmail.com");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomer(customerDTO);
        orderDTO.setId(1);
        orderDTO.setCreateDate(new Date());

        Orders orders = new Orders();
        orders.setCustomerId(customer);
        orders.setId(1);
        orders.setCreateDate(new Date());

        Books books =new Books();

        books.setQuantity(5);
        books.setTitle("denemeKitap");
        books.setIsbn("123456");

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setQuantity(5);
        orderDetails.setAmount(BigDecimal.valueOf(1233.00));
        orderDetails.setBookId(books);

        orders.setOrderDetails(Arrays.asList(orderDetails));

        Pageable pageable = PageRequest.of(0,25);

        when(customerRepository.findById(customer.getId()))
                .thenReturn(Optional.of(customer));

        when(ordersRepository.findAllByCustomerId(customer, pageable))
                .thenReturn(Arrays.asList(orders));

        List<OrderDTO> ordersOfCustomer = customerService.getAllOrdersOfCustomer(123456, 0, 25);

        Assert.assertEquals(ordersOfCustomer.get(0).getId() , orderDTO.getId());

    }

}
