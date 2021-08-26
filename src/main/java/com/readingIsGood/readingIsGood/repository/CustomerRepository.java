package com.readingIsGood.readingIsGood.repository;

import com.readingIsGood.readingIsGood.models.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Optional<Customer> findByEmailAdress(String email);

}
