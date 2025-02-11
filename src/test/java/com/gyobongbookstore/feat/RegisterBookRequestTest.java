package com.gyobongbookstore.feat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.*;

class RegisterBookRequestTest {


    private BookRepository bookRepository;

    private RegisterBook registerBook;

    @BeforeEach
    public void setData() {
        bookRepository = new BookRepository();
        registerBook = new RegisterBook(bookRepository);
    }

    @Test
    @DisplayName("신규 도서를 생성한다.")
    void registerBook() {

        final Long id = 1L;
        final String author = "author";
        final String title = "title";
        final Set<Category> categories = Set.of(Category.HUMANITIES, Category.ECONOMICSMANAGEMENT);

        RegisterBookRequest request = new RegisterBookRequest(id, author, title, categories);

        registerBook.register(request);

        Assertions.assertThat(bookRepository.findAll()).hasSize(1);

    }

    private record RegisterBookRequest(
            Long id,
            String author,
            String title,
            Set<Category> categories) {

        RegisterBookRequest {
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

        public void assignId(final Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
    }

    private class RegisterBook {

        private final BookRepository bookRepository;

        private RegisterBook(final BookRepository bookRepository) {
            this.bookRepository = bookRepository;
        }

        public void register(RegisterBookRequest request) {

            final Book book = request.toDomain();

            bookRepository.save(book);
        }
    }

    private class BookRepository {

        private Map<Long, Book> books = new HashMap<>();
        private Long sequence = 1L;

        public void save(Book book) {
            book.assignId(sequence++);
            books.put(book.getId(), book);
        }

        public List<Book> findAll() {

            return new ArrayList<>(books.values());
        }
    }
}
