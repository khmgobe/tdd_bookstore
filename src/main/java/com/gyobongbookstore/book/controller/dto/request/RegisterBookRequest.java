package com.gyobongbookstore.book.controller.dto.request;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record RegisterBookRequest(

        @NotBlank(message = "지은이는 필수입니다.")
        String author,

        @NotBlank(message = "제목은 필수입니다.")
        String title,

        @NotNull(message = "도서 상태는 필수입니다.")
        BookCondition bookCondition,

        @NotNull(message = "대여 가능 상태는 필수입니다. ")
        RentalStatus rentalStatus,

        @NotNull(message = "카테고리는 필수입니다.")
        Set<Category> categories) {

    public Book toDomain() {
        return Book.builder()
                .author(author)
                .title(title)
                .bookCondition(bookCondition)
                .rentalStatus(rentalStatus)
                .categories(categories).build();
    }
}
