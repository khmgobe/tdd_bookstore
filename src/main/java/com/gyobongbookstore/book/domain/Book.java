package com.gyobongbookstore.book.domain;

import com.gyobongbookstore.book.domain.enumeration.Category;
import org.springframework.util.Assert;

import java.util.Set;

public class Book {

    private Long id;
    private final String author;
    private final String title;
    private final Set<Category> categories;

    public Book(
            final String author,
            final String title,
            final Set<Category> categories) {
        this.author = author;
        this.title = title;
        this.categories = categories;

        validateConstructor(author, title, categories);
    }

    private void validateConstructor(
            final String author,
            final String title,
            final Set<Category> categories) {

        Assert.notNull(author, "지은이는 필수입니다.");
        Assert.notNull(title, "제목은 필수입니다.");
        Assert.notNull(categories, "카테고리는 필수입니다.");
    }

    public void assignId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
