package com.gyobongbookstore.feat.api;

import com.gyobongbookstore.book.domain.Book;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.book.repository.BookRepository;
import com.gyobongbookstore.common.ApiTest;
import com.gyobongbookstore.common.TestScenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class RentalBookApiTest extends ApiTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("도서를 대여한다.")
    void rentalBook()  {

        TestScenario.registerBook().request();

        final Long id = 1L;

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .patch("/api/v1/books/{bookId}/rental", id)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        final Book book = bookRepository.getBy(id);

        assertThat(book.getRentalStatus()).isEqualTo(RentalStatus.RENTED);
    }
}
