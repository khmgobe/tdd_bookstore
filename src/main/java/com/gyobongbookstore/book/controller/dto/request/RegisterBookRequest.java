package com.gyobongbookstore.book.controller.dto.request;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Schema(description = "도서 등록 데이터")
public record RegisterBookRequest(

        @Schema(description = "도서 지은이")
        @NotBlank(message = "지은이는 필수입니다.")
        String author,

        @Schema(description = "도서 제목")
        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @Schema(description = "도서 상태")
        @NotNull(message = "도서 상태는 필수입니다.")
        BookCondition bookCondition,

        @Schema(description = "대여 가능 상태")
        @NotNull(message = "대여 가능 상태는 필수입니다.")
        RentalStatus rentalStatus,

        @Schema(description = "카테고리")
        @NotNull(message = "카테고리는 필수입니다.")
        List<Category> categories) {

    public Book toDomain() {
        return Book.builder()
                .author(author)
                .title(title)
                .bookCondition(bookCondition)
                .rentalStatus(rentalStatus)
                .categories(categories).build();
    }
}
