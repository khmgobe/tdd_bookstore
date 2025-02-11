package com.gyobongbookstore.book.controller.dto.response;

import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import lombok.Builder;

import java.util.List;

@Builder
public record FindBooksResponse(String author,
                                String title,
                                BookCondition bookCondition,
                                RentalStatus rentalStatus,
                                List<Category> categories) {
}
