package com.mil0812.mybookstore.persistence.repository.contracts;

import com.mil0812.mybookstore.persistence.entity.impl.Authors;
import com.mil0812.mybookstore.persistence.entity.impl.Book;
import com.mil0812.mybookstore.persistence.repository.Repository;
import java.util.Set;

/**
 * Кастомний запит до сутності "книги", що показує книги конкретного автора
 */
public interface CommentRepository extends Repository<Book> {
  Set<Book> findAllByAuthor(Authors author);
}
