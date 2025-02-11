package com.gyobongbookstore.book.controller;

import com.gyobongbookstore.book.service.RentalBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "RENTAL-BOOK", description = "도서 대여 API")
@RestController
@RequiredArgsConstructor
class RentalBookController {

    private final RentalBookService rentalBookService;

    @Operation(
            summary = "도서 대여",
            description = "도서를 대여합니다.",

            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "도서 대여 성공",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PatchMapping("/api/v1/books/{bookId}/rental")
    public ResponseEntity<Void> rental(
            @Parameter(description = "도서 아이디", required = true)
            @PathVariable final Long bookId) {

        rentalBookService.rental(bookId);

        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }
}