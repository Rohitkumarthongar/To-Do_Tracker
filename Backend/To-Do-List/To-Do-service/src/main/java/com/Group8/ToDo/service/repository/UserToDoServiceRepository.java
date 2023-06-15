package com.Group8.ToDo.service.repository;

import com.Group8.ToDo.service.exceeptin.UserNotFoundException;
import com.Group8.ToDo.service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToDoServiceRepository extends MongoRepository<User,String> {

    User findByUserEmailId(String userEmailId) throws UserNotFoundException;
    User findByUserEmailIdAndUserName(String userName,String UserEmailId) throws UserNotFoundException;
}
