package dev.dialvive.interview.walmart.exercise345.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "isbn")
    private String id;

    @Column(name = "author")
    private String author;

    @Column(name = "publication")
    private Timestamp date;

}
