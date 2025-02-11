package com.gyobongbookstore.feat;
import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.repository.BookRepository;
import com.gyobongbookstore.common.ApiTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.*;

class RegisterBookServiceRequestTest extends ApiTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("신규 도서를 생성한다.")
    void registerBook() {

        final Long id = 1L;
        final String author = "author";
        final String title = "title";
        final Set<Category> categories = Set.of(Category.HUMANITIES, Category.ECONOMICSMANAGEMENT);

        RegisterBookRequest request = new RegisterBookRequest(id, author, title, categories);

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/v1/books")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(bookRepository.findAll()).hasSize(1);
    }
}
