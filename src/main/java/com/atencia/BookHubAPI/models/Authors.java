package com.atencia.BookHubAPI.models;

import com.atencia.BookHubAPI.dto.DataAuthors;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToOne
    private Books books;

    public Authors() {
    }

    public Authors(DataAuthors author) {

        this.name = String.valueOf(author.name());
        this.birthYear = Integer.valueOf(String.valueOf(author.birthYear()));
        this.deathYear = Integer.valueOf(String.valueOf(author.deathYear()));

    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
