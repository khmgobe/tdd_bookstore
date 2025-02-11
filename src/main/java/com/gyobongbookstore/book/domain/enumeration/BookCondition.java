package com.gyobongbookstore.book.domain.enumeration;

public enum BookCondition {
    NORMAL("정상"),
    DAMAGED("훼손"),
    LOST("분실");

    private final String description;

    BookCondition(final String description) {
        this.description = description;
    }
}
