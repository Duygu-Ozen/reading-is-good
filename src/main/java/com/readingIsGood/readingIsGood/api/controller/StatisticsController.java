package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.constants.ApiEndpoints;
import com.readingIsGood.readingIsGood.models.dto.OrderStatisticDTO;
import com.readingIsGood.readingIsGood.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ApiEndpoints.API_BASE_URL_STATISTICS)
public class StatisticsController {

    @Autowired
    IStatisticsService iStatisticsService;

    @GetMapping("/{customerId}")
    public List<OrderStatisticDTO> getStatisticValues(@PathVariable Integer customerId) {
        return iStatisticsService.getOrderStatisticValues(customerId);
    }

}
