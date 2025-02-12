package com.gyobongbookstore.book.service;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalBookService {

    private final BookRepository bookRepository;

    @Transactional
    public void rental(final Long bookId) {

        final Book book = bookRepository.getBy(bookId);

        book.rental();
    }
}