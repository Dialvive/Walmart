package dev.dialvive.interview.walmart.exercise345.controller;

import dev.dialvive.interview.walmart.exercise345.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> findById(@PathVariable final String id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok().body(book.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    @Transactional()
    public ResponseEntity<Book> create(@Validated @RequestBody final Book book) {
        // Is this book published in the future?
        Instant nowInstant = Instant.now();
        Timestamp nowTimestamp = Timestamp.from(nowInstant);
        entityManager.getTransaction().begin();
        if (book.getDate().after(nowTimestamp)) { // Yes, rollback!
            entityManager.getTransaction().rollback();
        }
        // No, continue!
        ResponseEntity<Book> response;
        Example<Book> bookExample = Example.of(book);
        if (bookRepository.exists(bookExample)) { // Already exists, merge
            entityManager.merge(book);
            response = ResponseEntity.ok().body(book);
        } else { // New book, persist
            entityManager.persist(book);
            response = (ResponseEntity<Book>) ResponseEntity.created(URI.create(book.getId()));
        }
        entityManager.getTransaction().commit();
        return response;
    }

    @PostMapping("/author/{id}")
    public Iterable<Book> findByAuthor(@PathVariable final String id){
        Book book = new Book();
        book.setAuthor(id);
        Example<Book> bookExample = Example.of(book);
        return bookRepository.findAll(bookExample);
    }

    @PostMapping("/after/{timestamp}")
    public Iterable<Book> findByDateAfter(@PathVariable final Timestamp timestamp){
        return bookRepository.findByDateAfter(timestamp);
    }

}
