package com.gyobongbookstore.book.domain;

import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.book.domain.enumeration.Category;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.util.Assert;

import java.util.Set;

@Entity
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
    @Comment("도서 상태")
    private BookCondition bookCondition;

    @Column(name = "book_rental_status", nullable = false)
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
}
