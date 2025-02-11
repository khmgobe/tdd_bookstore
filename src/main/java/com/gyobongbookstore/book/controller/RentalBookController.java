package com.gyobongbookstore.book.controller;

import com.gyobongbookstore.book.service.RentalBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class RentalBookController {

    private final RentalBookService rentalBookService;

    @PatchMapping("/api/v1/books/{bookId}/rental")
    public ResponseEntity<Void> rental(@PathVariable final Long bookId) {

        rentalBookService.rental(bookId);

        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}