package com.Group8.ToDo.Archieve.Service.controller;

import com.Group8.ToDo.Archieve.Service.exception.TaskAlreadyExistException;
import com.Group8.ToDo.Archieve.Service.exception.TaskNotFoundException;
import com.Group8.ToDo.Archieve.Service.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
import com.Group8.ToDo.Archieve.Service.model.Task;
import com.Group8.ToDo.Archieve.Service.model.User;
import com.Group8.ToDo.Archieve.Service.service.UserArchieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/to-do/archieveService")
public class ArchieveController {

    private UserArchieveService service;
    ResponseEntity responseEntity;

    @Autowired
    public ArchieveController(UserArchieveService service) {
        this.service = service;
    }


//    @PostMapping("/registerUser")
//    public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyRegistered {
//        System.out.println("Chewck 1");
//        try {
//            responseEntity = new ResponseEntity(service.registerToArchieve(user), HttpStatus.CREATED);
//        } catch (UserAlreadyRegistered e) {
//            throw new RuntimeException(e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            responseEntity = new ResponseEntity("Internal server Error ...pls Try Later", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }

//    @PutMapping("/updateProfile/{userEmailId}")
//    ResponseEntity<?> updateProfile(@PathVariable String userEmailId,@RequestBody User user)
//    {
//        try
//        {
//            responseEntity=new ResponseEntity(service.updateProfile(userEmailId, user), HttpStatus.OK);
//        } catch (Exception e)
//        {
//            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }


    @PostMapping("/addTask/{userEmailId}")
    ResponseEntity<?> addTaskToUser(@RequestBody Task task, @PathVariable String userEmailId) throws UserNotFoundException, TaskAlreadyExistException {
        System.out.println("User archieve service Working For Add Data");
        try {
            responseEntity = new ResponseEntity<>(service.addTask(task, userEmailId), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (TaskAlreadyExistException t) {
            throw new TaskAlreadyExistException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/updateTask/{userEmailId}/{taskId}")
    ResponseEntity<?> updateTask(@PathVariable String userEmailId,@RequestBody Task task,@PathVariable int taskId) throws UserNotFoundException
    {
        try
        {
            responseEntity=new ResponseEntity(service.updateTask(userEmailId, taskId, task),HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            responseEntity=new ResponseEntity("User Not Found Exception",HttpStatus.OK);
        } catch (Exception e) {
            responseEntity=new ResponseEntity("Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

//    @PutMapping("/resetPassword/{userEmailId}/{userName}")
//    ResponseEntity<?> resetPassword(@PathVariable String userEmailId,@PathVariable String userName,@RequestBody User user) throws UserNotFoundException
//    {
//        System.out.println("USer check");
//        try
//        {
//            responseEntity=new ResponseEntity(service.forgotPassword(userEmailId, userName, user),HttpStatus.OK);
//            System.out.println("User checked");
//        } catch (UserNotFoundException u)
//        {
//            responseEntity=new ResponseEntity("User Not Found Exception",HttpStatus.NOT_FOUND);
//        }catch (Exception e)
//        {
//            responseEntity=new ResponseEntity("Internal Server error Pls Check ....",HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return responseEntity;
//    }

    @GetMapping("/allTask/{userEmailId}")
    ResponseEntity<?> getAllTask(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.getAllTask(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

   @DeleteMapping("/delete/{userEmailId}/{taskId}")
    ResponseEntity <?> DeleteTask(@PathVariable String userEmailId ,@PathVariable int taskId) throws  UserNotFoundException, TaskNotFoundException
    {
        {
            responseEntity =new ResponseEntity(service.deleteTask(taskId,userEmailId),HttpStatus.OK);
        }
        return responseEntity;
    }



    }
