package com.readingIsGood.readingIsGood.service;


import com.readingIsGood.readingIsGood.models.dto.OrderStatisticDTO;
import com.readingIsGood.readingIsGood.models.entity.OrderDetails;
import com.readingIsGood.readingIsGood.models.entity.Orders;
import com.readingIsGood.readingIsGood.repository.OrderDetailsRepository;
import com.readingIsGood.readingIsGood.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements IStatisticsService {

    private final OrdersRepository ordersRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public StatisticsServiceImpl(OrdersRepository ordersRepository, OrderDetailsRepository orderDetailsRepository) {
        this.ordersRepository = ordersRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Override
    @Transactional
    public List<OrderStatisticDTO> getOrderStatisticValues(int customerId) {

        List<OrderStatisticDTO> result = new ArrayList<>();
        List<Orders> customerOrders = ordersRepository.findAllByCustomerId_Id(customerId);

        Map<String, List<Orders>> ordersByMonthMap = customerOrders.stream().collect(Collectors.groupingBy(orders -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(orders.getCreateDate());
            return cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        }));

        ordersByMonthMap.forEach((month, orders) -> {
            OrderStatisticDTO orderStatisticDTO = new OrderStatisticDTO();
            orderStatisticDTO.setMonth(month);
            orderStatisticDTO.setTotalOrderCount(orders.size());

            orders.forEach(order -> {
                List<OrderDetails> orderDetails = orderDetailsRepository.findAllByOrderId_Id(order.getId());
                double orderTotalPurchasedAmount = orderDetails.stream().mapToDouble(od -> od.getAmount().doubleValue()).sum();
                orderStatisticDTO.setTotalPurchasedAmount(BigDecimal.valueOf(orderTotalPurchasedAmount));

                int totalPurchasedBooks = orderDetails.stream().mapToInt(OrderDetails::getQuantity).sum();

                orderStatisticDTO.setTotalBookCount(totalPurchasedBooks);

            });

            result.add(orderStatisticDTO);
        });

        return result;
    }
}
