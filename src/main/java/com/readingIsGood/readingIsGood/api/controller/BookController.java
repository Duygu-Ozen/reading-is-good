package com.readingIsGood.readingIsGood.api.controller;

import com.readingIsGood.readingIsGood.api.constants.ApiEndpoints;
import com.readingIsGood.readingIsGood.api.request.BookRequest;
import com.readingIsGood.readingIsGood.models.dto.BookDTO;
import com.readingIsGood.readingIsGood.models.entity.Books;
import com.readingIsGood.readingIsGood.service.IBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping(value = ApiEndpoints.API_BASE_URL_BOOKS)
public class BookController {

    private IBooksService iBooksService;

    @Autowired
    public BookController(IBooksService iBooksService) {
        this.iBooksService = iBooksService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@Valid @RequestBody BookRequest request) {
        return iBooksService.saveBook(request);
    }

    @PutMapping("/update")
    public ResponseEntity<BookDTO> update(@RequestBody Books book) {

        if (Objects.isNull(iBooksService.updateBook(book)))
            return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

}
