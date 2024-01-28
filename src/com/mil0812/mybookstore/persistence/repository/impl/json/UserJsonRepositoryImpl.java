package com.mil0812.mybookstore.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mil0812.mybookstore.persistence.entity.impl.User;
import com.mil0812.mybookstore.persistence.repository.impl.json.GenericJsonRepository;
import com.mil0812.mybookstore.persistence.repository.contracts.UserRepository;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;

/**
 *Клас, призначений для обробки сутності "користувач",
 * яка використовує JSON як базовий формат зберігання
 */
public final class UserJsonRepositoryImpl
    extends GenericJsonRepository<User>
    implements UserRepository {

  public UserJsonRepositoryImpl(Gson gson) {
    super(gson, JsonPathFactory.USERS.getPath(), TypeToken
        .getParameterized(Set.class, User.class)
        .getType());
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return entities.stream().filter(u -> u.getUsername().equals(username)).findFirst();
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return entities.stream().filter(u -> u.getEmail().equals(email)).findFirst();
  }
}
