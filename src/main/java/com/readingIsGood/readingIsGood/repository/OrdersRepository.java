package com.readingIsGood.readingIsGood.repository;

import com.readingIsGood.readingIsGood.models.entity.Customer;
import com.readingIsGood.readingIsGood.models.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {

    List<Orders> findAllByCustomerId_Id(int customerId);

    List<Orders> findAllByCustomerId(Customer customerId, Pageable pageable);

    List<Orders> findAllByCreateDateBetween(Date startDate, Date endDate);

}
