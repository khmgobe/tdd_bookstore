package com.gyobongbookstore.book.domain;

import com.google.common.annotations.VisibleForTesting;
import com.gyobongbookstore.book.controller.dto.response.FindBooksResponse;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.book.domain.enumeration.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "도서 도메인 객체")
@Entity
@Table(name = "books")
@Comment("도서 테이블")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Schema(description = "도서 아이디")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    @Comment("도서 아이디")
    private Long id;

    @Schema(description = "도서 지은이", requiredMode = Schema.RequiredMode.REQUIRED, example = "book_author")
    @Column(name = "book_author", nullable = false)
    @Comment("도서 지은이")
    private String author;

    @Schema(description = "도서 제목", requiredMode = Schema.RequiredMode.REQUIRED, example = "book_title")
    @Column(name = "book_title", nullable = false)
    @Comment("도서 제목")
    private String title;

    @Schema(description = "도서 상태", requiredMode = Schema.RequiredMode.REQUIRED, example = "NORMAL")
    @Column(name = "book_condition", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("도서 상태")
    private BookCondition bookCondition;

    @Schema(description = "도서 대여 가능 상태", requiredMode = Schema.RequiredMode.REQUIRED, example = "AVAILABLE")
    @Getter
    @Column(name = "book_rental_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Comment("대여 가능 상태")
    private RentalStatus rentalStatus;

    @Schema(description = "도서 카테고리 (1개 이상)", requiredMode = Schema.RequiredMode.REQUIRED, example = "LITERATURE, SCIENCE")
    @Enumerated(EnumType.STRING)
    @Column(name = "categories", nullable = false)
    @ElementCollection(targetClass = Category.class)
    @CollectionTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"))
    private List<Category> categories = new ArrayList<>();

    @Builder
    private Book(
            final String author,
            final String title,
            final BookCondition bookCondition,
            final RentalStatus rentalStatus,
            final List<Category> categories) {
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
            final List<Category> categories) {
        this(author, title, bookCondition, rentalStatus, categories);
        this.id = id;
    }

    private void validateConstructor(
            final String author,
            final String title,
            final BookCondition bookCondition,
            final RentalStatus rentalStatus,
            final List<Category> categories) {

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

        if (rentalStatus == RentalStatus.UNAVAILABLE) {
            throw new IllegalArgumentException("대여할 수 없는 도서입니다.");
        }
    }

    public FindBooksResponse toResponse() {

        return FindBooksResponse.builder()
                .author(author)
                .title(title)
                .bookCondition(bookCondition)
                .rentalStatus(rentalStatus)
                .categories(categories)
                .build();
    }
}
