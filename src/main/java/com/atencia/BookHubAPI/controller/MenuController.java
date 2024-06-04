package com.atencia.BookHubAPI.controller;

import com.atencia.BookHubAPI.dto.Data;
import com.atencia.BookHubAPI.dto.DataAuthors;
import com.atencia.BookHubAPI.dto.DataBooks;
import com.atencia.BookHubAPI.models.Authors;
import com.atencia.BookHubAPI.models.Books;
import com.atencia.BookHubAPI.service.ApiService;
import com.atencia.BookHubAPI.service.BooksService;
import com.atencia.BookHubAPI.service.ConvertData;
import com.atencia.BookHubAPI.service.ConvertDataImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
public class MenuController {

    private static final ApiService apiService = new ApiService();
    private static final ConvertData convertData = new ConvertDataImpl();
    private final BooksService booksService;
    private static final String URL_BASE = "https://gutendex.com/books/";
    private static final Scanner scanner = new Scanner(System.in);
    private static final String SEPARATOR = "==============================================================================";

    public MenuController(BooksService booksService) {
        this.booksService = booksService;
    }

    public void viewMenu() throws JsonProcessingException, UnsupportedEncodingException, URISyntaxException {
        String nameBook = getUserInput();
        Data data = fetchDataFromApi(nameBook);
        Optional<DataBooks> book = findBookByName(data.results(), nameBook);
        book.ifPresentOrElse(this::saveAndDisplayBookDetails, () -> System.out.println("Book not found."));
    }

    public void displayBooksSearchHistory() {
        List<Books> booksList = booksService.getAllBooks();
        displayHistoryHeader("📜 Books Search History");

        if (booksList.isEmpty()) {
            System.out.println("No books found in search history.");
            return;
        }

        for (Books book : booksList) {
            displayBookInfo(book);
        }
    }

    public void displayBooksSearchLanguages() {

        System.out.print("Please enter the languages you want to search books in:");
        String languages = scanner.nextLine();
        List<Books> booksList = booksService.searchBooksByLanguages(languages);
        displayHistoryHeader("🌐 Books Search By Languages: " + languages.toUpperCase());

        if (booksList.isEmpty()) {
            System.out.println("No books found in search languages.");
            return;
        }

        for (Books book : booksList) {
            displayBookInfo(book);
        }

    }

    public void displayAuthorsOfBooksSearchHistory() {
        List<Books> booksList = booksService.getAllBooks();
        displayHistoryHeader("📜 Authors of Books Search History");

        if (booksList.isEmpty()) {
            System.out.println("No books found in search history.");
            return;
        }

        for (Books book : booksList) {
            displayAuthorsInfo(book);
        }
    }

    private Data fetchDataFromApi(String nameBook) throws JsonProcessingException, UnsupportedEncodingException, URISyntaxException {

        String encodedQuery = URLEncoder.encode(nameBook, StandardCharsets.UTF_8);
        URI uri = new URI(URL_BASE + "?search=" + encodedQuery);

        String dataApi = apiService.getDataApi(String.valueOf(uri));
        return convertData.getData(dataApi, Data.class);

    }

    public void displayTopBooks() throws JsonProcessingException {

        List<Books> books = booksService.top5ByOrderByDownload();


        displayHistoryHeader(" 🏆 Top 5 Most Downloaded Books");
        System.out.println(SEPARATOR);
        books.stream()
                .limit(5)
                .map(book -> "* " + book.getTitle().toUpperCase())
                .forEach(System.out::println);
        System.out.println(SEPARATOR);

    }

    public void findAuthorsAliveInYear() {

        System.out.print("Please enter the year in which to search for living authors: ");
        try {

            int year = scanner.nextInt();
            List<Authors> authors = booksService.findAuthorsAliveInYear(year);

            if (authors.isEmpty()) {
                displayHistoryHeader(" ⚰️ There are no living authors for this date: " + year);
                return;
            }

            displayHistoryHeader(" 🎂 There are living authors for this date: " + year);
            System.out.println("╔════════════════════════════════════════════════════════╗");
            authors.stream()
                    .map(author -> "     ✒️ " + author.getName() + " (🎂 " + author.getBirthYear() + " - ⚰️ " + author.getDeathYear() + ")")
                    .forEach(System.out::println);
            System.out.println("╚════════════════════════════════════════════════════════╝");

        } catch (NumberFormatException e) {
            System.out.println("\n🔢 Please enter an integer.\n");
        }

    }


    private String getUserInput() {
        System.out.print("Enter the name of the book to search: ");
        return scanner.nextLine();
    }

    private Optional<DataBooks> findBookByName(List<DataBooks> books, String nameBook) {
        return books.stream()
                .filter(b -> b.title().toUpperCase().contains(nameBook.toUpperCase()))
                .findFirst();
    }

    private void saveAndDisplayBookDetails(DataBooks book) {
        saveBookDetails(book);
        displayBookDetails(book);
    }

    private void displayBookDetails(DataBooks book) {
        System.out.println("\n" + SEPARATOR);
        String authorsInfo = getAuthorsInfo(book.authors());
        String language = book.languages().isEmpty() ? "Unknown" : String.valueOf(book.languages());

        System.out.printf("📖 Title: %s%n🖊️ Author(s): %s%n📥 Download: %s%n🌐 Language: %s%n",
                book.title(), authorsInfo, book.download(), language);
        System.out.println(SEPARATOR);
    }

    private void saveBookDetails(DataBooks bookDetails) {

        try {

            Books book = new Books(bookDetails);
            List<Authors> authors = bookDetails.authors().stream().map(Authors::new).toList();
            book.setAuthors(authors);
            booksService.createBooks(book);

        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                displayHistoryHeader("The book *" + bookDetails.title() + "* already exists in the database.");
            }
        }

    }

    private void displayBookInfo(Books book) {
        System.out.println(SEPARATOR);
        String authorsInfo = getAuthors(book.getAuthors());
        String language = book.getLanguages().isEmpty() ? "Unknown" : String.valueOf(book.getLanguages());

        System.out.printf("📖 Title: %s%n🖊️ Author(s): %s%n📥 Download: %s%n🌐 Language: %s%n",
                book.getTitle(), authorsInfo, book.getDownload(), language);
        System.out.println(SEPARATOR);
    }

    private void displayAuthorsInfo(Books book) {
        String authorsInfo = getAuthors(book.getAuthors());
        if (!authorsInfo.isEmpty()) {
            System.out.println(SEPARATOR);
            System.out.printf("🖊️ Author(s): %s%n", authorsInfo);
            System.out.println(SEPARATOR);
        }
    }

    private void displayHistoryHeader(String title) {
        System.out.println("\n╔══════════════════════════════════════════════════╗");
        System.out.println(" " + title + " ");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }

    private String getAuthorsInfo(List<DataAuthors> authors) {
        return authors.stream()
                .map(author -> author.name() + " (🎂 " + author.birthYear() + " - ⚰️ " + author.deathYear() + ")")
                .collect(Collectors.joining("\n"));
    }

    private String getAuthors(List<Authors> authors) {
        return authors.stream()
                .map(author -> author.getName() + " (🎂 " + author.getBirthYear() + " - ⚰️ " + author.getDeathYear() + ")")
                .collect(Collectors.joining("\n"));
    }
}
