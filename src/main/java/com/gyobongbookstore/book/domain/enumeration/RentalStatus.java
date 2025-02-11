package com.gyobongbookstore.book.domain.enumeration;

public enum RentalStatus {

    AVAILABLE ("대여 가능"),
    UNAVAILABLE ("대여 중단"),
    RENTED("대여중");

    private final String descripton;

    RentalStatus(final String descripton) {
        this.descripton = descripton;
    }
}
