package com.gyobongbookstore.book.repository;

import com.gyobongbookstore.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository <Book,Long> {

}
