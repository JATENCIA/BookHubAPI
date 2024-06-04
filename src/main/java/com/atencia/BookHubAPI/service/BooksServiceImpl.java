package com.atencia.BookHubAPI.service;

import com.atencia.BookHubAPI.models.Authors;
import com.atencia.BookHubAPI.models.Books;
import com.atencia.BookHubAPI.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BooksServiceImpl implements BooksService {

    BooksRepository booksRepository;

    public BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public void createBooks(Books books) {

        booksRepository.save(books);

    }

    @Override
    public List<Books> getAllBooks() {

        return booksRepository.findAll();

    }

    @Override
    public List<Authors> findAuthorsAliveInYear(Integer year) {

        return booksRepository.findAuthorsAliveInYear(year);

    }

    @Override
    public List<Books> searchBooksByLanguages(String languages) {

        List<Books> books = getAllBooks();

        if (books.isEmpty()) return null;

        List<Books> list = new ArrayList<>();
        for (Books book : books) {
            if (book.getLanguages().stream().anyMatch(b -> b.toUpperCase().contains(languages.toUpperCase()))) {
                list.add(book);
            }
        }

        return list;

    }


    @Override
    public List<Books> top5ByOrderByDownload() {

        return booksRepository.findTop5ByOrderByDownloadDesc();

    }
}
