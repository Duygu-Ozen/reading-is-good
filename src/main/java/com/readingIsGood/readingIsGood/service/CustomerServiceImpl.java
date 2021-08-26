package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.CustomerRequest;
import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.models.entity.Customer;
import com.readingIsGood.readingIsGood.models.entity.Orders;
import com.readingIsGood.readingIsGood.repository.CustomerRepository;
import com.readingIsGood.readingIsGood.repository.OrdersRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private CustomerRepository customerRepository;
    private OrdersRepository ordersRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrdersRepository ordersRepository) {
        this.customerRepository = customerRepository;
        this.ordersRepository = ordersRepository;
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrdersOfCustomer(Integer customerNo, Integer pageNo, Integer pageSize) {

        Customer customer = customerRepository.findById(customerNo)
                .orElseThrow(() -> new NoSuchElementException("Customer not found for id:" + customerNo));

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        List<Orders> ordersList = ordersRepository.findAllByCustomerId(customer, pageable);

        return ordersList.stream().map(Orders::toDTO).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public CustomerDTO saveCustomer(CustomerRequest request) {

        Optional<Customer> customerOptional = customerRepository.findByEmailAdress(request.getEmailAdress());
        if (customerOptional.isPresent())
            throw new IllegalArgumentException("Customer is exist with email:" + request.getEmailAdress());

        Customer customer = new Customer();
        customer.setBuildingNo(request.getBuildingNo());
        customer.setCity(request.getCity());
        customer.setEmailAdress(request.getEmailAdress());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setFlatNo(request.getFlatNo());
        customer.setPhoneNumber(request.getPhoneNumber());
        customer.setNip(request.getNip());
        customer.setPostalCode(request.getPostalCode());
        customer.setStreet(request.getStreet());

        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.toDTO();
    }





}
