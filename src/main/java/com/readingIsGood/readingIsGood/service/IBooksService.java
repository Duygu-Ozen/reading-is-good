package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.BookRequest;
import com.readingIsGood.readingIsGood.models.dto.BookDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;


public interface IBooksService {

    public BookDTO saveBook(BookRequest request);

    public BookDTO updateBook(Books books);

}
