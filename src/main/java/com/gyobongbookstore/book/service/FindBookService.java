package com.gyobongbookstore.book.service;

import com.gyobongbookstore.book.controller.dto.response.FindBooksResponse;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindBookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<FindBooksResponse> findAllBooksByCategories(final Category category) {

        return bookRepository.findByCategories(category).stream().map(Book::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<FindBooksResponse> findByAuthorAndTitle(final String author, final String title) {

        return bookRepository.findByAuthorAndTitle(author, title).stream().map(Book::toResponse).toList();
    }
}