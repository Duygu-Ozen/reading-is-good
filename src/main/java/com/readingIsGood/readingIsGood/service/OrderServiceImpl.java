package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.OrderRequest;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;
import com.readingIsGood.readingIsGood.models.entity.Customer;
import com.readingIsGood.readingIsGood.models.entity.OrderDetails;
import com.readingIsGood.readingIsGood.models.entity.Orders;
import com.readingIsGood.readingIsGood.repository.BooksRepository;
import com.readingIsGood.readingIsGood.repository.CustomerRepository;
import com.readingIsGood.readingIsGood.repository.OrderDetailsRepository;
import com.readingIsGood.readingIsGood.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    private OrdersRepository ordersRepository;
    private OrderDetailsRepository orderDetailsRepository;
    private CustomerRepository customerRepository;
    private BooksRepository booksRepository;

    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository, OrderDetailsRepository orderDetailsRepository, CustomerRepository customerRepository, BooksRepository booksRepository) {
        this.ordersRepository = ordersRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerRepository = customerRepository;
        this.booksRepository = booksRepository;
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrderByDateInterval(Date startDate, Date endDate) {
        List<Orders> ordersList = ordersRepository.findAllByCreateDateBetween(startDate, endDate);
        return ordersList.stream().map(Orders::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDTO saveOrder(OrderRequest request) {

        Orders order = new Orders();
        OrderDetails orderDetails = new OrderDetails();

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new NoSuchElementException("Customer not found for id:" + request.getCustomerId()));

        order.setCustomerId(customer);
        order.setCreateDate(new Date());
        order.setState(request.getState());

        Orders orderSaved = ordersRepository.save(order);

        request.getOrderDetailsRequest().forEach(orderDetailRequest -> {

            Books book = booksRepository.findByIsbn(orderDetailRequest.getBookId())
                    .orElseThrow(() -> new NoSuchElementException("Book not found for isbn:" + orderDetailRequest.getBookId()));

            if (book.getQuantity() >= orderDetailRequest.getQuantity() && book.getAvailableQuantity() >= orderDetailRequest.getQuantity()) {
                orderDetails.setOrderId(orderSaved);
                orderDetails.setAmount(orderDetailRequest.getAmount());
                orderDetails.setBookId(book);
                orderDetails.setQuantity(orderDetailRequest.getQuantity());

                orderDetailsRepository.save(orderDetails);

                int newQuantity = book.getQuantity() - orderDetailRequest.getQuantity();
                int newAvailableQuantity = book.getAvailableQuantity() - orderDetailRequest.getQuantity();
                book.setQuantity(newQuantity);
                book.setAvailableQuantity(newAvailableQuantity);
                booksRepository.save(book);
            } else
                throw new IllegalArgumentException("Quantity is not enough for book :" + orderDetailRequest.getBookId());

        });

        return order.toDTO();
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrderById(Integer customerId) {
        List<Orders> ordersList = ordersRepository.findAllByCustomerId_Id(customerId);
        return ordersList.stream().map(Orders::toDTO).collect(Collectors.toList());
    }

}
