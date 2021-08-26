package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.constants.ApiEndpoints;
import com.readingIsGood.readingIsGood.api.request.CustomerRequest;
import com.readingIsGood.readingIsGood.models.dto.CustomerDTO;
import com.readingIsGood.readingIsGood.models.dto.OrderDTO;
import com.readingIsGood.readingIsGood.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = ApiEndpoints.API_BASE_URL_CUSTOMER)
public class CustomerController {

    private ICustomerService iCustomerService;

    @Autowired
    public CustomerController(ICustomerService iCustomerService) {
        this.iCustomerService = iCustomerService;
    }

    @GetMapping("/{customerNo}/orders")
    public List<OrderDTO> getOrdersOfCustomer(@PathVariable Integer customerNo,
                                              @RequestParam(defaultValue = "25") Integer pageSize,
                                              @RequestParam(defaultValue = "0") Integer pageNo) {

        if (pageSize > 25) throw new IllegalArgumentException("Page size can be 25 at most");
        return iCustomerService.getAllOrdersOfCustomer(customerNo, pageNo, pageSize);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@Valid @RequestBody CustomerRequest request) {
        return iCustomerService.saveCustomer(request);
    }


}
