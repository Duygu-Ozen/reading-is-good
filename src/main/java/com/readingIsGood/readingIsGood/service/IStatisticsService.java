package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.models.dto.OrderStatisticDTO;

import java.util.List;

public interface IStatisticsService {

    public List<OrderStatisticDTO> getOrderStatisticValues(int customerId);

}
