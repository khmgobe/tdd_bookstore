package com.gyobongbookstore.book.repository;

import com.gyobongbookstore.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book,Long> {

    default Book getBy(final Long bookId) {
        return findById(bookId).orElseThrow(() ->
                new IllegalArgumentException("해당 도서가 존재하지 않습니다. %d".formatted(bookId)));
    }
}
