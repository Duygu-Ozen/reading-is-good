package com.readingIsGood.readingIsGood.models.entity;

import com.readingIsGood.readingIsGood.models.dto.BookDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @Column(name = "isbn", unique = true)
    private String isbn;

    private String title;
    private Date publicationDate;
    private int edition;
    private double price;
    private String author;
    private String publisher;

    @Column(name = "available_quantity")
    private int availableQuantity;
    private int quantity;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BookDTO toDTO(){

        BookDTO bookDTO = new BookDTO();

        bookDTO.setAuthor(author);
        bookDTO.setEdition(edition);
        bookDTO.setAvailableQuantity(availableQuantity);
        bookDTO.setQuantity(quantity);
        bookDTO.setIsbn(isbn);
        bookDTO.setPrice(price);
        bookDTO.setPublisher(publisher);
        bookDTO.setPublicationDate(publicationDate);
        bookDTO.setTitle(title);

        return bookDTO;

    }
}
