package com.gyobongbookstore.feat.domain;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BookTest {

    @Test
    @DisplayName("도서를 대여한다. 도서를 대여하면 대여 상태가 [RENTED]로 변경된다.")
    void rentalBook() {

        final Book book = BookFixture
                .anBook()
                .build();

        book.rental();

        assertThat(book.getRentalStatus().equals(RentalStatus.RENTED));

    }

    @Test
    @DisplayName("훼손된 도서의 대여를 시도한다. 훼손한 도서의 대여를 시도할 시 예외가 발생한다")
    void fail_invalid_condition_damaged() {

        final Book book = BookFixture
                .anBook()
                .bookCondition(BookCondition.DAMAGED)
                .build();

        Assertions
                .assertThatThrownBy(book::rental)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("훼손된 도서는 대여할 수 없습니다.");

    }

    @Test
    @DisplayName("분실된 도서의 대여를 시도한다. 분실한 도서의 대여를 시도할 시 예외가 발생한다")
    void fail_invalid_condition_lost() {

        final Book book = BookFixture
                .anBook()
                .bookCondition(BookCondition.LOST)
                .build();

        Assertions
                .assertThatThrownBy(book::rental)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("분실된 도서는 대여할 수 없습니다.");
    }


    @Test
    @DisplayName("도서의 대여를 시도한다. 대여중인 도서를 대여하려고 하면, 예외가 발생한다.")
    void fail_invalid_book_rented() {

        final Book book = BookFixture
                .anBook()
                .rentalStatus(RentalStatus.RENTED)
                .build();

        assertThatThrownBy(book::rental)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 대여중인 도서입니다.");
    }

    @Test
    @DisplayName("도서의 대여를 시도한다. 대여가 중단된 도서를 대여하려고 하면, 예외가 발생한다.")
    void fail_invalid_book_unavailable() {

        final Book book = BookFixture
                .anBook()
                .rentalStatus(RentalStatus.UNAVAILABLE)
                .build();

        assertThatThrownBy(book::rental)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("대여할 수 없는 도서입니다.");
    }

    @Test
    @DisplayName("도서의 카테고리 변경을 시도한다. 기존과 동일한 카테고리를 추가하려고 할 시, 추가되지 않는다.")
    void add_category() {

        final Book book = BookFixture
                .anBook()
                .bookCondition(BookCondition.NORMAL)
                .rentalStatus(RentalStatus.UNAVAILABLE)
                .categories(Arrays.asList(Category.HUMANITIES, Category.IT))
                .build();

        book.addCategory(Category.HUMANITIES);

        Assertions.assertThat(book.getCategories().size()).isEqualTo(2);
    }
}
