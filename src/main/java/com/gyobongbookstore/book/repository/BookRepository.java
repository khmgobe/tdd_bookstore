package com.gyobongbookstore.book.repository;

import com.gyobongbookstore.book.domain.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookRepository {

    private Map<Long, Book> books = new HashMap<>();
    private Long sequence = 1L;

    public void save(Book book) {
        book.assignId(sequence++);
        books.put(book.getId(), book);
    }

    public List<Book> findAll() {

        return new ArrayList<>(books.values());
    }
}
