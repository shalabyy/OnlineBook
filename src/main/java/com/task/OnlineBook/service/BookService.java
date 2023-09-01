package com.task.OnlineBook.service;

import com.task.OnlineBook.model.Book;
import com.task.OnlineBook.dto.Bookdto;
import com.task.OnlineBook.service.mapper.Mapper;
import com.task.OnlineBook.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookService {

    private BookRepository bookRepository;

    private final Mapper<Book, Bookdto> mapper;

    @Autowired
    public BookService(BookRepository bookRepository, Mapper<Book, Bookdto> mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<Bookdto> getAllBooks() {
        List<Bookdto> bookdto = new ArrayList<>();
        try {
            log.info("book Repository: " + bookRepository.findAll().toString());
            bookRepository.findAll().forEach(book -> bookdto.add(mapper.toDtoObject(book)));
            return bookdto;
        } catch (Exception e){
            log.error("get all books service error: " + e.toString());
            return null;
        }
    }

    public List<Bookdto> getBooksByCategory(String category) {
        List<Bookdto> bookdto = new ArrayList<>();
        try {
            bookRepository.findByCategory(category).forEach(book -> bookdto.add(mapper.toDtoObject(book)));
            return bookdto;
        } catch (Exception e){
            log.error("get all books by Category service error: " + e.toString());
            return bookdto  ;
        }
    }

    public Bookdto getBookById(Integer id) {
        try {
            return mapper.toDtoObject(bookRepository.findById(id).orElse(null));
        } catch (Exception e){
            log.error("get all books by id service error: " + e.toString());
            return null;
        }
    }

    public Bookdto borrowBook(Integer id) {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null && book.isStock()) {
                book.setStock(false);
                return mapper.toDtoObject(bookRepository.save(book));
            }
            return null;
        } catch (Exception e){
            log.error("borrow Book service error: " + e.toString());
            return null;
        }
    }

    public Bookdto returnBook(Integer id) {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                book.setStock(true);
                return mapper.toDtoObject(bookRepository.save(book));
            }
            return null;
        } catch (Exception e){
            log.error("return Book service error: " + e.toString());
            return null;
        }
    }

    public Bookdto addBook(Bookdto bookdto) {
        try {
            Book book = mapper.toEntityObject(bookdto);
            return mapper.toDtoObject(bookRepository.save(book));
        } catch (Exception e){
            log.error("add Book service error: " + e.toString());
            return null;
        }
    }

    public Bookdto updateBook(Integer id, Bookdto updatedBook) {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setStock(updatedBook.isStock());
                return mapper.toDtoObject(bookRepository.save(book));
            }
            return null;
        } catch (Exception e){
            log.error("update Book service error: " + e.toString());
            return null;
        }
    }
    @Transactional
    public boolean deleteBook(Integer id) {
        try {
            log.info("delete Book service with id: " + id);
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                bookRepository.delete(book);
                return true;
            }
            return false;
        } catch (Exception e){
            log.error("delete Book service error: " + e.toString());
            return false;
        }
    }
}
