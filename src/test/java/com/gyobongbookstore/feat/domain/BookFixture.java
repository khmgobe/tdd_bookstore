package com.gyobongbookstore.feat.domain;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;

import java.util.Set;

public class BookFixture {

    private Long bookId = 1L;
    private String author = "bookauthor";
    private String title = "booktitle";
    private BookCondition bookCondition = BookCondition.NORMAL;
    private RentalStatus rentalStatus = RentalStatus.AVAILABLE;
    private Set<Category> categories = Set.of(Category.HUMANITIES, Category.ECONOMICSMANAGEMENT);

    public BookFixture bookCondition(final BookCondition bookCondition) {
        this.bookCondition = bookCondition;
        return this;
    }

    public BookFixture rentalStatus(final RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
        return this;
    }

    public static BookFixture anBook() {
        return new BookFixture();
    }

    public Book build() {
        return new Book(bookId, author, title, bookCondition, rentalStatus, categories);
    }
}
