package com.readingIsGood.readingIsGood.repository;

import com.readingIsGood.readingIsGood.models.entity.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BooksRepository extends CrudRepository<Books, String> {

    public Optional<Books> findByIsbn(String isbn);

}
