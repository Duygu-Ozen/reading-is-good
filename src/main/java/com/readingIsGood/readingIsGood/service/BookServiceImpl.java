package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.BookRequest;
import com.readingIsGood.readingIsGood.models.dto.BookDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;
import com.readingIsGood.readingIsGood.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBooksService {

    @Autowired
    BooksRepository booksRepository;

    @Override
    @Transactional
    public BookDTO saveBook(BookRequest request) {

        Optional<Books> booksOptional = booksRepository.findByIsbn(request.getIsbn());
        if (booksOptional.isPresent())
            throw new IllegalArgumentException("Book already exist for isbn:" + request.getIsbn());

        Books book = new Books();
        book.setAuthor(request.getAuthor());
        book.setEdition(request.getEdition());
        book.setIsbn(request.getIsbn());
        book.setPublisher(request.getPublisher());
        book.setTitle(request.getTitle());
        book.setEdition(request.getEdition());
        book.setPrice(request.getPrice());
        book.setPublicationDate(request.getPublicationDate());
        book.setQuantity(request.getQuantity());
        book.setAvailableQuantity(request.getQuantity());

        Books books = booksRepository.save(book);

        return books.toDTO();
    }

    @Override
    @Transactional
    public BookDTO updateBook(Books book) {

        booksRepository.findByIsbn(book.getIsbn())
                .orElseThrow(() -> new NoSuchElementException("Book not found for isbn:" + book.getIsbn()));

        Books books = booksRepository.save(book);
        return books.toDTO();

    }
}
