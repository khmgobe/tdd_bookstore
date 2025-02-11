package com.gyobongbookstore.book.service;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterBook {

    private final BookRepository bookRepository;

    public RegisterBook(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public void register(RegisterBookRequest request) {

        final Book book = request.toDomain();

        bookRepository.save(book);
    }
}
