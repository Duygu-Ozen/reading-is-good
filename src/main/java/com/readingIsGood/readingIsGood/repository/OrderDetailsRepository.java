package com.readingIsGood.readingIsGood.repository;

import com.readingIsGood.readingIsGood.models.entity.OrderDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

    public List<OrderDetails> findAllByOrderId_Id(int orderId);
}
