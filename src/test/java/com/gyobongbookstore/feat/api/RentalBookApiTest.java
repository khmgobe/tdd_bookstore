package com.gyobongbookstore.feat.api;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.book.repository.BookRepository;
import com.gyobongbookstore.common.ApiTest;
import com.gyobongbookstore.common.TestScenario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class RentalBookApiTest extends ApiTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    @DisplayName("도서를 대여한다.")
    void rentalBook() {

        TestScenario
                .registerBook().request()
                .rentalBook().request();

        final Book book = bookRepository.getBy(1L);

        assertThat(book.getRentalStatus()).isEqualTo(RentalStatus.RENTED);
    }
}
