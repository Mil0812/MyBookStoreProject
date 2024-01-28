package com.mil0812.mybookstore.domain.impl;

import com.mil0812.mybookstore.domain.exception.AuthException;
import com.mil0812.mybookstore.domain.exception.UserAlreadyAuthException;
import com.mil0812.mybookstore.persistence.entity.impl.User;
import com.mil0812.mybookstore.persistence.repository.contracts.UserRepository;
import org.mindrot.bcrypt.BCrypt;

public class AuthService {
  //////
  private final UserRepository userRepository;
  private User user;

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  public boolean authentificate(String username, String password){
    if(user!=null){
      throw new UserAlreadyAuthException("Ви вже авторизувалися як %s"
          .formatted(user.getUsername()));
    }
    User foundUser = userRepository.findByUsername(username)
        .orElseThrow(AuthException::new);
    if(!BCrypt.checkpw(password, foundUser.getPassword())){
      return false;
    }
    user = foundUser;
    return true;
  }
  public boolean isAuthentificated(){
    return user != null;
  }
  public User getUser(){
    return user;
  }
  public void logOut(){
    if(user == null){
      throw new UserAlreadyAuthException("Ви ще не аутентифіковані");
    }
    user = null;
  }
}
