package com.Group8.ToDo.Archieve.Service.repository;


import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
import com.Group8.ToDo.Archieve.Service.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArchieveRepository extends MongoRepository<User,String> {

    User findByUserEmailId(String userEmailId) throws UserNotFoundException;

    User findByUserEmailIdAndUserName(String userName,String userEmailId) throws UserNotFoundException;

}
