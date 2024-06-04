# BookHubAPI

BookHubAPI is a project that leverages the Gutenberg Open Book API to provide various functionalities related to
searching books, managing book data, and exploring authors.

## Introduction

BookHubAPI is a Spring Boot application that integrates with the Gutendex API to offer a range of features for book
enthusiasts. Users can search for books, view their details, access historical search data, find top downloaded books,
and explore authors alive in a specific year.

## Features

- **Search Books**: Users can search for books by title and view detailed book information.
- **Historical Search Data**: Display search history of previously accessed books.
- **Top Downloaded Books**: View the top 5 most downloaded books.
- **Search By Authors**: Explore authors related to books in the search history.
- **Search By Languages**: Filter books based on the languages they are available in.
- **Authors Alive In Year**: Find authors who were alive during a specific year.

## Technologies Used

- Spring Boot
- Hibernate
- Gutendex API
- PostgreSQL (for database storage)
- Jackson (for JSON processing)
- Maven (for project management)

## Setup

1. Clone this repository to your local machine.
2. Update the application.properties file with your database configurations.
3. Build the project using Maven.
4. Run the application using `mvn spring-boot:run` or deploy the generated JAR file.

## Usage

1. Launch the application.
2. Select from the available menu options to perform various book-related tasks.
3. Utilize the search functionalities and explore the world of books and authors.

