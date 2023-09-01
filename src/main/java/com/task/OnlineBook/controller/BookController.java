package com.task.OnlineBook.controller;

import com.task.OnlineBook.dto.Bookdto;
import com.task.OnlineBook.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    private final BookService bookService;

    @Autowired
    public  BookController(BookService bookService){
            this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Bookdto>> getAllBooks() {
        try {
            log.info("Start get All Books");
            return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookdto> getBookById(@PathVariable Integer id) {
        try {
            log.info("Start get Book By id:" + id);
            return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Bookdto>> getBooksByCategory(@PathVariable String category) {
        try {
            log.info("Start get Books By category:" + category);
            return new ResponseEntity<>(bookService.getBooksByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<Bookdto> borrowBook(@PathVariable Integer id) {
        try {
            log.info("Start borrowBook with id:" + id);
            return new ResponseEntity<>(bookService.borrowBook(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Bookdto> returnBook(@PathVariable Integer id) {
        try {
            log.info("Start returnBook with id:" + id);
            return new ResponseEntity<>(bookService.returnBook(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @PostMapping
    public ResponseEntity<Bookdto> addBook(@RequestBody Bookdto book) {
        try {
            log.info("Start addBook with Book:" + book.toString());
            return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bookdto> updateBook(@PathVariable Integer id, @RequestBody Bookdto updatedBook) {
        try {
            log.info("Start updateBook with id:" + id);

            return new ResponseEntity<>(bookService.updateBook(id,updatedBook), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        try {
            log.info("Start deleteBook with id:" + id);
            if (bookService.deleteBook(id)) {
                return ResponseEntity.ok("Book deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}

