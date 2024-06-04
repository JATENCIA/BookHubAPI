package com.atencia.BookHubAPI.service;

import com.atencia.BookHubAPI.models.Authors;
import com.atencia.BookHubAPI.models.Books;

import java.util.List;

public interface BooksService {

    public void createBooks(Books books);

    public List<Books> getAllBooks();

    public List<Authors> findAuthorsAliveInYear(Integer year);

    public List<Books> searchBooksByLanguages(String languages);

    public List<Books> top5ByOrderByDownload();


}
