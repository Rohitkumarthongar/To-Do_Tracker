package com.Group8.ToDo.Authentication.repository;

import com.Group8.ToDo.Authentication.exception.UserNotFoundException;
import com.Group8.ToDo.Authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,String>{
    User findByUserEmailIdAndUserPassword(String userEmailId,String userPassword) throws UserNotFoundException;
    User findByUserEmailIdAndUserName(String userName,String UserEmailId) throws UserNotFoundException;
}
