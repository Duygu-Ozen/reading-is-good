package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.constants.ApiEndpoints;
import com.readingIsGood.readingIsGood.api.request.OrderRequest;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = ApiEndpoints.API_BASE_URL_ORDERS)
public class OrderController {

    private IOrderService iOrderService;

    @Autowired
    public OrderController(IOrderService iOrderService) {

        this.iOrderService = iOrderService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@Valid @RequestBody OrderRequest request) {

        return iOrderService.saveOrder(request);
    }

    @GetMapping("/by-date-interval")
    public List<OrderDTO> getOrdersByDateInterval(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return iOrderService.getOrderByDateInterval(startDate, endDate);
    }

    @GetMapping("/by-customer-id")
    public List<OrderDTO> getOrdersById(@RequestParam Integer customerId) {
        return iOrderService.getOrderById(customerId);
    }

}
