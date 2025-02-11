package com.gyobongbookstore.book.domain;

import com.google.common.annotations.VisibleForTesting;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.book.domain.enumeration.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.Set;

@Entity
@Getter
@Table(name = "books")
@Comment("도서 테이블")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @Comment("도서 아이디")
    private Long id;

    @Column(name = "book_author", nullable = false)
    @Comment("도서 지은이")
    private String author;

    @Column(name = "book_title", nullable = false)
    @Comment("도서 제목")
    private String title;

    @Column(name = "book_condition", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("도서 상태")
    private BookCondition bookCondition;

    @Column(name = "book_rental_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("대여 가능 상태")
    private RentalStatus rentalStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    @ElementCollection(targetClass = Category.class)
    @CollectionTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"))
    private Set<Category> categories;

    @Builder
    private Book(
            final String author,
            final String title,
            final BookCondition bookCondition,
            final RentalStatus rentalStatus,
            final Set<Category> categories) {
        this.author = author;
        this.title = title;
        this.bookCondition = bookCondition;
        this.rentalStatus = rentalStatus;
        this.categories = categories;

        validateConstructor(author, title, bookCondition, rentalStatus, categories);
    }

    @VisibleForTesting
    public Book(
            final Long id,
            final String author,
            final String title,
            final BookCondition bookCondition,
            final RentalStatus rentalStatus,
            final Set<Category> categories) {
        this(author, title, bookCondition, rentalStatus, categories);
        this.id = id;
    }

    private void validateConstructor(
            final String author,
            final String title,
            final BookCondition bookCondition,
            final RentalStatus rentalStatus,
            final Set<Category> categories) {

        Assert.notNull(author, "지은이는 필수입니다.");
        Assert.notNull(title, "제목은 필수입니다.");
        Assert.notNull(bookCondition, "도서 상태는 필수입니다.");
        Assert.notNull(rentalStatus, "대여 가능 상태는 필수입니다.");
        Assert.notNull(categories, "카테고리는 필수입니다.");
    }

    private void validateBookCondition() {
        if (bookCondition != BookCondition.NORMAL) {
            rentalStatus = RentalStatus.UNAVAILABLE;
        }
    }

    public void rental() {
        validateRentalStatus();
        validateBookCondition();

        rentalStatus = RentalStatus.RENTED;
    }

    private void validateRentalStatus() {
        if (rentalStatus == RentalStatus.RENTED) {
            throw new IllegalArgumentException("이미 대여중인 도서입니다.");
        }

        if(rentalStatus == RentalStatus.UNAVAILABLE) {
            throw new IllegalArgumentException("대여할 수 없는 도서입니다.");
        }
    }
}
