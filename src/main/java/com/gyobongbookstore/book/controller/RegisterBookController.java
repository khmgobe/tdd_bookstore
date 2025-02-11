package com.gyobongbookstore.book.controller;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.service.RegisterBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "REGISTER-BOOK", description = "도서 등록 API")
@RestController
@RequiredArgsConstructor
class RegisterBookController {

    private final RegisterBookService registerBookService;

    @PostMapping("/api/v1/books")
    @Operation(
            summary = "도서 등록",
            description = "새로운 도서를 등록합니다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "도서 등록을 위한 요청 데이터",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterBookRequest.class)
                    )
            ),
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "201",
                            description = "도서 등록 성공",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    public ResponseEntity<Void> register(
            @RequestBody @Valid RegisterBookRequest request) {

        final Book book = request.toDomain();
        registerBookService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED.value()).build();
    }
}
