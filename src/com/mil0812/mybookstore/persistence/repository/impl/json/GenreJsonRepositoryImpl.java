package com.mil0812.mybookstore.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mil0812.mybookstore.persistence.entity.impl.Book;
import com.mil0812.mybookstore.persistence.entity.impl.Genre;
import com.mil0812.mybookstore.persistence.entity.impl.User;
import com.mil0812.mybookstore.persistence.repository.contracts.GenreRepository;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

public final class GenreJsonRepositoryImpl
extends GenericJsonRepository<Genre>
implements GenreRepository {

  public GenreJsonRepositoryImpl(Gson gson) {
    super(gson,JsonPathFactory.GENRES.getPath(), TypeToken
        .getParameterized(Set.class, Genre.class)
        .getType());
  }

  @Override
  public Optional<Genre> findAllByBook(Book book) {
    return entities.stream().filter(g -> g.getGenreName().equals(book.getGenre())).findFirst();
  }


}
