package com.gyobongbookstore.book.domain.enumeration;

public enum BookCondition {
    NORMAL("정상") {
        @Override
        public void validateBookCondition() {

        }
    },
    DAMAGED("훼손") {
        @Override
        public void validateBookCondition() {
            throw new IllegalArgumentException("훼손된 도서는 대여할 수 없습니다.");
        }
    },
    LOST("분실") {
        @Override
        public void validateBookCondition() {
            throw new IllegalArgumentException("분실된 도서는 대여할 수 없습니다.");
        }
    };

    private final String description;

    BookCondition(final String description) {
        this.description = description;
    }

    public abstract void validateBookCondition();
}
