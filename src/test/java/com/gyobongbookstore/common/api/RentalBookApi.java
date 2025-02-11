package com.gyobongbookstore.common.api;

import com.gyobongbookstore.common.TestScenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

public class RentalBookApi {

    private Long id = 1L;

    public TestScenario request() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .patch("/api/v1/books/{bookId}/rental", id)
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        return new TestScenario();
    }
}
