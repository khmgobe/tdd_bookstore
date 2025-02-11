package com.gyobongbookstore.book.service;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterBook {

    private final BookRepository bookRepository;

    @PostMapping("/api/v1/books")
    @Transactional
    public ResponseEntity<Void> register(@RequestBody RegisterBookRequest request) {

        final Book book = request.toDomain();

        bookRepository.save(book);

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
