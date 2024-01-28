package com.mil0812.mybookstore.persistence.repository.factory;

import com.mil0812.mybookstore.persistence.repository.json.contracts.CommentRepository;
import com.mil0812.mybookstore.persistence.repository.json.contracts.UserRepository;

public interface RepositoryFactory {
CommentRepository newCommentRepository();
UserRepository newUserRepository();
////і всі інші, що створю

}
