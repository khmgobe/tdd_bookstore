package com.gyobongbookstore.book.controller;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.service.FindBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "FIND-BOOK", description = "도서 조회 API")
@RestController
@RequiredArgsConstructor
class FindBookController {

    private final FindBookService findBookService;

    @Operation(
            summary = "카테고리 기반 도서 검색",
            description = "카테고리 기반으로 도서를 조회합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "카테고리 기반 도서 목록 조회 성공",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping("api/v1/books/category")
    public List<Book> findAllBooksByCategory(
            @Parameter(description = "도서 카테고리", required = true)
            @RequestParam Category category) {
        return findBookService.findAllBooksByCategories(category);
    }

    @Operation(
            summary = "지은이, 제목 기반 도서 검색",
            description = "지은이, 제목 기반으로 도서를 조회합니다.",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                            responseCode = "200",
                            description = "지은이, 제목 기반 도서 목록 조회 성공",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping("api/v1/books/search")
    public List<Book> findByAuthorAndTitle(
            @Parameter(description = "도서 지은이", required = true)
            @RequestParam final String author,
            @Parameter(description = "도서 제목", required = true)
            @RequestParam final String title) {
        return findBookService.findByAuthorAndTitle(author, title);
    }
}
