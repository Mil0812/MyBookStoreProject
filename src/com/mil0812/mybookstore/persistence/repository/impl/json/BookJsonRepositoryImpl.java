package com.mil0812.mybookstore.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mil0812.mybookstore.persistence.entity.impl.Authors;
import com.mil0812.mybookstore.persistence.entity.impl.Book;
import com.mil0812.mybookstore.persistence.repository.contracts.BookRepository;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public final class BookJsonRepositoryImpl
extends GenericJsonRepository<Book>
implements BookRepository {

  public BookJsonRepositoryImpl(Gson gson) {
    super(gson,JsonPathFactory.BOOKS.getPath(), TypeToken
        .getParameterized(Set.class, Book.class)
        .getType());
  }

  @Override
  public Set<Book> findAllByAuthor(Authors author) {
    return  entities.stream().filter(c -> c.getAuthor().equals(author))
        .collect(Collectors.toSet());
  }
}
