package com.gyobongbookstore.book.domain.enumeration;

public enum RentalStatus {

    AVAILABLE ("대여 가능") {
        @Override
        public void validateRental() {

        }
    },
    UNAVAILABLE ("대여 중단") {
        @Override
        public void validateRental() {
            throw new IllegalArgumentException("대여할 수 없는 도서입니다.");
        }
    },
    RENTED("대여중") {
        @Override
        public void validateRental() {
                throw new IllegalArgumentException("이미 대여중인 도서입니다.");
        }
    };

    private final String descripton;

    RentalStatus(final String descripton) {
        this.descripton = descripton;
    }

    public abstract void validateRental();
}
