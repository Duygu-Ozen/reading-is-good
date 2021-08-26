package com.readingIsGood.readingIsGood.service;

import com.readingIsGood.readingIsGood.api.request.BookRequest;
import com.readingIsGood.readingIsGood.models.dto.BookDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;
import com.readingIsGood.readingIsGood.repository.BooksRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @Mock
    BooksRepository booksRepository;

    @InjectMocks
    BookServiceImpl bookService;

    private BookRequest request;

    @Before
    public void init() {

        request = new BookRequest();
        request.setIsbn("123456");
        request.setAuthor("author");
        request.setEdition(1);
        request.setQuantity(5);
        request.setPrice(10);
        request.setPublicationDate(new Date());
        request.setTitle("title");
        request.setPublisher("publisher");

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSaveBookWhenBookIsExist() {

        Optional<Books> books = Optional.of(mock(Books.class));
        Mockito.when(booksRepository.findByIsbn(Mockito.any())).thenReturn(books);

        bookService.saveBook(request);

    }

    @Test
    public void testSaveBookWhenBookIsNotExist() {

        Books books = new Books();
        books.setIsbn("123456");
        books.setAuthor("author");
        books.setEdition(1);
        books.setQuantity(5);
        books.setPrice(10);
        books.setPublicationDate(new Date());
        books.setTitle("title");
        books.setPublisher("publisher");
        books.setAvailableQuantity(5);

        Mockito.when(booksRepository.findByIsbn(Mockito.any())).thenReturn(Optional.empty());
        Mockito.when(booksRepository.save(Mockito.any(Books.class))).thenReturn(books);

        BookDTO bookDTO = bookService.saveBook(request);

        Assert.assertEquals(bookDTO.getAuthor(), request.getAuthor());
        Assert.assertEquals(bookDTO.getIsbn(), request.getIsbn());
        Assert.assertEquals(bookDTO.getEdition(), request.getEdition());
        Assert.assertEquals(bookDTO.getQuantity(), request.getQuantity());
        Assert.assertTrue(bookDTO.getPrice() == request.getPrice());
        Assert.assertEquals(bookDTO.getAvailableQuantity(), request.getQuantity());
        Assert.assertEquals(bookDTO.getPublisher(), request.getPublisher());
        Assert.assertEquals(bookDTO.getPublicationDate(), request.getPublicationDate());
        Assert.assertEquals(bookDTO.getTitle(), request.getTitle());

    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateBookWhenBookIsNotExist() {

        Books books = new Books();
        books.setIsbn("123456");

        Mockito.when(booksRepository.findByIsbn(books.getIsbn())).thenReturn(Optional.empty());
        bookService.updateBook(books);

    }

    @Test
    public void testUpdateBookWhenBookIsExist() {

        Books books = new Books();
        books.setIsbn("123456");

        Books newBook = new Books();
        newBook.setIsbn("123456");
        newBook.setAuthor("author");
        newBook.setEdition(1);
        newBook.setQuantity(5);
        newBook.setPrice(10);
        newBook.setPublicationDate(new Date());
        newBook.setTitle("title");
        newBook.setPublisher("publisher");
        newBook.setAvailableQuantity(5);

        Mockito.when(booksRepository.findByIsbn(Mockito.any())).thenReturn(Optional.of(books));
        Mockito.when(booksRepository.save(Mockito.any(Books.class))).thenReturn(newBook);

        BookDTO bookDTO = bookService.updateBook(newBook);

        Assert.assertEquals(bookDTO.getAuthor(), newBook.getAuthor());
        Assert.assertEquals(bookDTO.getIsbn(), newBook.getIsbn());
        Assert.assertEquals(bookDTO.getEdition(), newBook.getEdition());
        Assert.assertEquals(bookDTO.getQuantity(), newBook.getQuantity());
        Assert.assertTrue(bookDTO.getPrice() == newBook.getPrice());
        Assert.assertEquals(bookDTO.getAvailableQuantity(), newBook.getQuantity());
        Assert.assertEquals(bookDTO.getPublisher(), newBook.getPublisher());
        Assert.assertEquals(bookDTO.getPublicationDate(), newBook.getPublicationDate());
        Assert.assertEquals(bookDTO.getTitle(), newBook.getTitle());

    }

}
