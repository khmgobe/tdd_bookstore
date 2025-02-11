package com.gyobongbookstore.feat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Set;

class RegisterBookTest {


    @Test
    @DisplayName("신규 도서를 생성한다.")
    void registerBook()  {

        final Long id = 1L;
        final String author = "author";
        final String title = "title";
        final Set<Category> categories = Set.of(Category.HUMANITIES, Category.ECONOMICSMANAGEMENT);

        RegisterBook registerBook = new RegisterBook(id, author, title, categories);
    }

    private record RegisterBook(
            Long id,
            String author,
            String title,
            Set<Category> category) {

        RegisterBook {
            Assert.notNull(id, "아이디는 필수입니다.");
            Assert.notNull(author, "지은이는 필수입니다.");
            Assert.notNull(title, "제목은 필수입니다.");
            Assert.notNull(category, "카테고리는 필수입니다.");
        }
    }

    enum Category {
        IT("IT"),
        LITERATURE("문학"),
        ECONOMICSMANAGEMENT("경제경영"),
        HUMANITIES("인문학");

        final String description;

        Category(final String description) {
            this.description = description;
        }
    }
}
