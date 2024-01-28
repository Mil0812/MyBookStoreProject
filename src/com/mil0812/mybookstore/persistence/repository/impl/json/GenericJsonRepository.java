package com.mil0812.mybookstore.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.mil0812.mybookstore.persistence.entity.Entity;
import com.mil0812.mybookstore.persistence.exception.JsonFileIOException;
import com.mil0812.mybookstore.persistence.repository.Repository;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GenericJsonRepository<E extends Entity> implements Repository<E> {

  protected final Set<E> entities;
  private final Gson gson;
  private final Path path;
  private final Type collectionType;
  public GenericJsonRepository(Gson gson, Path path, Type collectionType){
    this.gson = gson;
    this.path = path;
    this.collectionType = collectionType;
    entities = new HashSet<>(loadAll());

  }

  /**
   * Пошук даних по id
   * @param id "айдішка"
   * @return "дані по першому символу, що відповідає заданому id"
   */
  @Override
  public Optional<E> findById(UUID id) {
    return entities.stream().filter (e-> e.getId().equals(id)).findFirst();
  }

  @Override
  public Set<E> findAll() {
    return entities;
  }

  /**
   * Пошук даних по фільтру
   * @param filter "фільтр"
   * @return "відфільтровані дані"
   */
  @Override
  public Set<E> findAll(Predicate<E> filter) {
    return entities.stream().filter(filter).collect(Collectors.toSet());
  }

  /**
   * Метод додавання даних ~Create from CRUD~
   * @param entity "сутність"
   * @return "додавання сутності в сутності"
   */
  @Override
  public E add(E entity) {
    entities.remove(entity);
    entities.add(entity);
    return null;
  }

  /**
   * Метод зчитування даних ~Read from CRUD~
   * @return "список потрібних даних"
   */
  private Set<E> loadAll() {
    try {
      fileNotFound();
      var json = Files.readString(path);
      return isJsonValid(json) ? gson.fromJson(json, collectionType) : new HashSet<>();
    } catch (IOException e) {
      throw new JsonFileIOException("Помилка при роботі із файлом %s"
          .formatted(path.getFileName()));
    }
  }

  /**
   * Метод видалення даних ~Delete from CRUD~
   * @param entity "сутність"
   * @return "видалення сутності із сутностей"
   */
  @Override
   public boolean remove(E entity) {
    return entities.remove(entity);
  }
  public Path getPath() {
    return path;
  }

  /**
   * Метод на випадок неіснування файлу: створення нового
   * @throws IOException "виключення при роботі із потоком вводу виводу"
   */
  private void fileNotFound() throws IOException {
    if (!Files.exists(path)) {
      Files.createFile(path);
    }
  }
  /**
   * Перевірка формату Json на валідність
   * @param input "JSON у форматі рядка"
   * @return "валідність: true/false "
   */
  private boolean isJsonValid(String input) {
    try (JsonReader reader = new JsonReader(new StringReader(input))) {
      reader.skipValue();
      return reader.peek() == JsonToken.END_DOCUMENT;
    } catch (IOException e) {
      return false;
    }
  }


}
