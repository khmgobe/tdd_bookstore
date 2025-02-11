package com.gyobongbookstore.book.controller.dto.request;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.Category;
import org.springframework.util.Assert;

import java.util.Set;

public record RegisterBookRequest(
        Long id,
        String author,
        String title,
        Set<Category> categories) {

    public RegisterBookRequest {
        Assert.notNull(author, "지은이는 필수입니다.");
        Assert.notNull(title, "제목은 필수입니다.");
        Assert.notNull(categories, "카테고리는 필수입니다.");
    }

    public Book toDomain() {
        return new Book(author, title, categories);
    }
}
