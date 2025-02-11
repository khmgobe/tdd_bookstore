package com.gyobongbookstore.book.controller;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.service.RegisterBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RegisterBookController {

    private final RegisterBookService registerBookService;

    public ResponseEntity<Void> register(@RequestBody RegisterBookRequest request) {

        final Book book = request.toDomain();
        registerBookService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
