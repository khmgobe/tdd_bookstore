package com.gyobongbookstore.book.repository;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository <Book,Long> {


    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c = :category")
    List<Book> findByCategories(@Param("category") Category category);

    List<Book> findByAuthorAndTitle(String author, String title);

    default Book getBy(final Long bookId) {
        return findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("해당 도서가 존재하지 않습니다. %d".formatted(bookId)));
    }
}
