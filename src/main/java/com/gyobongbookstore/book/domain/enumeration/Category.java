package com.gyobongbookstore.book.domain.enumeration;

public enum Category {
    IT("IT"),
    LITERATURE("문학"),
    ECONOMICSMANAGEMENT("경제경영"),
    HUMANITIES("인문학");

    final String description;

    Category(final String description) {
        this.description = description;
    }
}
