package dev.dialvive.interview.walmart.exercise345.controller;

import dev.dialvive.interview.walmart.exercise345.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface BookRepository extends CrudRepository<Book, String>, JpaRepository<Book, String> {

    Iterable<Book> findByAuthor(final String id);

    Iterable<Book> findByDateAfter(final Timestamp timestamp);
}
