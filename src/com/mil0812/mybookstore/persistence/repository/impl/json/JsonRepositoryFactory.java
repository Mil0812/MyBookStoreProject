package com.mil0812.mybookstore.persistence.repository.impl.json;

import com.mil0812.mybookstore.persistence.repository.RepositoryFactory;
import com.mil0812.mybookstore.persistence.repository.contracts.BookRepository;
import com.mil0812.mybookstore.persistence.repository.contracts.UserRepository;

/**
 * Клас - реалізація паттерну "UnitOfWork"
 */
public class JsonRepositoryFactory extends RepositoryFactory {


  @Override
  public BookRepository newCommentRepository() {
 //
  }

  @Override
  public UserRepository newUserRepository() {
    return new UserJsonRepositoryImpl(gson);
  }

  private JsonRepositoryFactory(){
    ///DataTimeFormatter...
  }


  private static class InstanceHolder{
    public static final JsonRepositoryFactory INSTANCE = new JsonRepositoryFactory();
  }
  public static JsonRepositoryFactory getInstance(){}
}
