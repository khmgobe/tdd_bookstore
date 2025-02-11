package com.gyobongbookstore.book.service;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterBookService {

    private final BookRepository bookRepository;

    @Transactional
    public void register(final RegisterBookRequest request) {

        final Book book = request.toDomain();

        bookRepository.save(book);
    }
}
