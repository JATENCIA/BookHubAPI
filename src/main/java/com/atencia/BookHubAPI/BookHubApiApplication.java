package com.atencia.BookHubAPI;

import com.atencia.BookHubAPI.controller.MenuController;
import com.atencia.BookHubAPI.controller.MenuOption;
import com.atencia.BookHubAPI.models.Books;
import com.atencia.BookHubAPI.repository.BooksRepository;
import com.atencia.BookHubAPI.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BookHubApiApplication implements CommandLineRunner {

    @Autowired
    BooksService booksService;



    public static void main(String[] args) {
        SpringApplication.run(BookHubApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        MenuOption.options(booksService);

    }
}
