package com.gyobongbookstore.feat.api;

import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.book.repository.BookRepository;
import com.gyobongbookstore.common.ApiTest;
import com.gyobongbookstore.common.TestScenario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FindBookApiTest extends ApiTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("도서의 저자와 제목을 이용하여 도서를 검색한다.")
    void search_for_books_by_author_and_title()  {

        final String bookAuthor = "khm";
        final String bookTitle = "khmtitle";

        TestScenario.registerBook()
                .author(bookAuthor)
                .title(bookTitle)
                .bookCondition(BookCondition.NORMAL)
                .rentalStatus(RentalStatus.AVAILABLE)
                .request();

        TestScenario.registerBook()
                .author(bookAuthor)
                .title(bookTitle)
                .bookCondition(BookCondition.LOST)
                .rentalStatus(RentalStatus.RENTED)
                .request();

        assertThat(bookRepository.findByAuthorAndTitle(bookAuthor, bookTitle)).hasSize(2);
    }

    @Test
    @DisplayName("카테고리로 도서를 검색한다.")
    void search_for_books_by_category()  {

        List<Category> bookCategories = Arrays.asList(Category.ECONOMICSMANAGEMENT, Category.HUMANITIES);

        TestScenario.registerBook()
                .categories(bookCategories)
                .title("khmgobe")
                .request();

        TestScenario.registerBook()
                .categories(bookCategories)
                .title("khmgobe2")
                .request();

        Assertions.assertThat(bookRepository.findByCategories(Category.ECONOMICSMANAGEMENT)).hasSize(2);

    }
}
