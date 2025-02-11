package com.gyobongbookstore.feat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Set;

class RegisterBookTest {


    @Test
    @DisplayName("신규 도서를 생성한다.")
    void registerBook() {

        final Long id = 1L;
        final String author = "author";
        final String title = "title";
        final Set<Category> categories = Set.of(Category.HUMANITIES, Category.ECONOMICSMANAGEMENT);

        RegisterBook registerBook = new RegisterBook(id, author, title, categories);
        Book book = registerBook.toDomain();
    }

    private record RegisterBook(
            Long id,
            String author,
            String title,
            Set<Category> categories) {

        RegisterBook {
            Assert.notNull(author, "지은이는 필수입니다.");
            Assert.notNull(title, "제목은 필수입니다.");
            Assert.notNull(categories, "카테고리는 필수입니다.");
        }

        public Book toDomain() {
            return new Book(author, title, categories);
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

    static class Book {

        private Long id;
        private final String author;
        private final String title;
        private final Set<Category> categories;

        public Book(
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
}
