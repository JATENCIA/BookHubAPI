package com.atencia.BookHubAPI.models;


import com.atencia.BookHubAPI.dto.DataBooks;
import jakarta.persistence.*;

import java.util.List;
import java.util.Optional;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @OneToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Authors> authors;
    private List<String> languages;
    private Double download;

    public Books() {
    }


    public Books(DataBooks book) {

        this.title = book.title();
        this.languages = book.languages();
        this.download = book.download();

    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Authors> authors) {
        authors.forEach(author -> author.setBooks(this));
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getDownload() {
        return download;
    }

    public void setDownload(Double download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", download=" + download +
                '}';
    }
}
