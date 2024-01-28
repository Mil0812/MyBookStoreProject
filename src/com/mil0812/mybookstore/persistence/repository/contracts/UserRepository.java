package com.mil0812.mybookstore.persistence.repository.json.contracts;

import com.mil0812.mybookstore.persistence.entity.impl.User;
import com.mil0812.mybookstore.persistence.repository.Repository;
import java.util.Optional;

/*
Ми визначаємо, з якою сутністю працбвати (додаємо дженерік)
Ми додаємо "кастомні" методи, актуальні саме для даної сутності
 */
public interface UserRepository extends Repository<User> {
  public Optional<User> findByUsername(String username);
  public Optional<User> findByEmail(String email);

}
