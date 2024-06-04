package com.atencia.BookHubAPI.repository;

import com.atencia.BookHubAPI.models.Authors;
import com.atencia.BookHubAPI.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    @Query("SELECT a FROM Authors a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    public List<Authors> findAuthorsAliveInYear(@Param("year") Integer year);

    public List<Books> findTop5ByOrderByDownloadDesc();

}

