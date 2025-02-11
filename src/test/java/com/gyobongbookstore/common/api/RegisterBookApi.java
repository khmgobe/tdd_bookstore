package com.gyobongbookstore.common.api;

import com.gyobongbookstore.book.controller.dto.request.RegisterBookRequest;
import com.gyobongbookstore.book.domain.enumeration.BookCondition;
import com.gyobongbookstore.book.domain.enumeration.Category;
import com.gyobongbookstore.book.domain.enumeration.RentalStatus;
import com.gyobongbookstore.common.TestScenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class RegisterBookApi {

    private String author = "author";
    private String title = "title";
    private BookCondition bookCondition = BookCondition.NORMAL;
    private RentalStatus rentalStatus = RentalStatus.AVAILABLE;
    private List<Category> categories = Arrays.asList(Category.HUMANITIES, Category.ECONOMICSMANAGEMENT);

    public RegisterBookApi author (final String author) {
        this.author = author;
        return this;
    }

    public RegisterBookApi title(final String title) {
        this.title = title;
        return this;
    }

    public RegisterBookApi rentalStatus(final RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
        return this;
    }

    public RegisterBookApi bookCondition(final BookCondition bookCondition) {
        this.bookCondition = bookCondition;
        return this;
    }

    public RegisterBookApi categories(final List<Category> categories) {
        this.categories = categories;
        return this;
    }

    public TestScenario request() {

        RegisterBookRequest request = new RegisterBookRequest(author, title, bookCondition, rentalStatus, categories);

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/api/v1/books")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value());

        return new TestScenario();
    }
}
