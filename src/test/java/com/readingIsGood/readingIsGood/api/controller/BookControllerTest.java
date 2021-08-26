package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.request.BookRequest;
import com.readingIsGood.readingIsGood.models.dto.BookDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;
import com.readingIsGood.readingIsGood.service.IBooksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private IBooksService iBooksService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void init() {

    }

    @Test
    public void testCreate() {

        BookRequest request = mock(BookRequest.class);

        BookDTO mockResult = new BookDTO();
        mockResult.setIsbn("test");

        Mockito.when(iBooksService.saveBook(request)).thenReturn(mockResult);

        Assert.assertEquals(iBooksService.saveBook(request).getIsbn(), mockResult.getIsbn());

    }

    @Test
    public void testUpdate() {

        Books request = mock(Books.class);

        BookDTO mockResult = new BookDTO();
        mockResult.setIsbn("test");

        Mockito.when(iBooksService.updateBook(request)).thenReturn(mockResult);

        Assert.assertEquals(iBooksService.updateBook(request).getIsbn(), mockResult.getIsbn());

    }
}
