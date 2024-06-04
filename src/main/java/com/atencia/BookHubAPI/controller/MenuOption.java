package com.atencia.BookHubAPI.controller;

import com.atencia.BookHubAPI.service.BooksService;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class MenuOption {

    private final static Scanner scanner = new Scanner(System.in);

    public static void options(BooksService booksService) {

        MenuController menuController = new MenuController(booksService);

        boolean flag = true;

        while (flag) {


            System.out.println("╔═══════════════════════════╗");
            System.out.println("         Options        ");
            System.out.println("╠═══════════════════════════╣");
            System.out.println(" 1 📚 Search Books ");
            System.out.println(" 2 📜 History Search By Name ");
            System.out.println(" 3 ✒️ Authors Of Books");
            System.out.println(" 4 📈 Top 5 Most Downloaded Books");
            System.out.println(" 5 ⏳ Authors Alive In Year");
            System.out.println(" 6 🌐 Books Search By Languages");
            System.out.println(" 0 🚪 Exit                  ");
            System.out.println("╚═══════════════════════════╝");


            System.out.print("\n👉 Select an option: ");


            try {

                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {

                    case 1:
                        menuController.viewMenu();
                        break;
                    case 2:
                        menuController.displayBooksSearchHistory();
                        break;
                    case 3:
                        menuController.displayAuthorsOfBooksSearchHistory();
                        break;
                    case 4:
                        menuController.displayTopBooks();
                        break;
                    case 5:
                        menuController.findAuthorsAliveInYear();
                        break;
                    case 6:
                        menuController.displayBooksSearchLanguages();
                        break;
                    case 0:
                        flag = false;
                        System.out.println("🚪 Exiting the system...");
                        break;
                    default:
                        System.out.println("❌ Invalid selection. Please choose a valid option.\n");
                        break;

                }

            } catch (NumberFormatException e) {
                System.out.println("\n🔢 Please enter an integer.\n");
            } catch (JsonProcessingException | UnsupportedEncodingException | URISyntaxException e) {
                throw new RuntimeException(e);
            }


        }

    }

}
