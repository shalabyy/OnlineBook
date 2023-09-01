package com.task.OnlineBook.service.mapper;

import com.task.OnlineBook.dto.Bookdto;
import com.task.OnlineBook.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;



@Slf4j
@Component
public class BookMapper extends Mapper<Book, Bookdto>{

    @Override
    public Book toEntityObject(Bookdto bookdto) {
        Book book = new Book();
        book.setId(bookdto.getId());
        book.setTitle(bookdto.getTitle());
        book.setAuthor(bookdto.getAuthor());
        book.setCategory(bookdto.getCategory());
        book.setStock(bookdto.isStock());
        return book;
    }

    @Override
    public Bookdto toDtoObject(Book book) {
        Bookdto bookdto = new Bookdto();
        bookdto.setId(book.getId());
        bookdto.setTitle(book.getTitle());
        bookdto.setAuthor(book.getAuthor());
        bookdto.setCategory(book.getCategory());
        bookdto.setStock(book.isStock());
        return bookdto;
    }
}
