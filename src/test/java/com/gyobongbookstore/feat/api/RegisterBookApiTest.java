package com.gyobongbookstore.feat.api;
import com.gyobongbookstore.book.repository.BookRepository;
import com.gyobongbookstore.common.ApiTest;
import com.gyobongbookstore.common.TestScenario;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RegisterBookApiTest extends ApiTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("신규 도서를 생성한다.")
    void registerBook() {

        TestScenario.registerBook().request();

        Assertions.assertThat(bookRepository.findAll()).hasSize(1);
    }
}
