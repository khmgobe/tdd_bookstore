package com.gyobongbookstore.book.controller;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.service.RegisterBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RegisterBookController {

    private final RegisterBookService registerBookService;

    @PostMapping("/api/v1/books")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterBookRequest request) {

        final Book book = request.toDomain();
        registerBookService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
