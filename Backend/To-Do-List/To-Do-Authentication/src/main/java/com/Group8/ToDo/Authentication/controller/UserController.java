package com.Group8.ToDo.Authentication.controller;

import com.Group8.ToDo.Authentication.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Authentication.exception.UserNotFoundException;
import com.Group8.ToDo.Authentication.model.User;
import com.Group8.ToDo.Authentication.service.EmailServiceAuth;
import com.Group8.ToDo.Authentication.service.SecurityTokenGenerator;
import com.Group8.ToDo.Authentication.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/to-do/auth")
public class UserController {

   private UserService service;
private EmailServiceAuth emailServiceAuth;
    private SecurityTokenGenerator token;
    ResponseEntity responseEntity;
    @Autowired
    public UserController(UserService service, SecurityTokenGenerator token, EmailServiceAuth emailServiceAuth) {
        this.service = service;
        this.token = token;
        this.emailServiceAuth = emailServiceAuth;
    }
//    Checked
    @PostMapping("/registerUser")
    public ResponseEntity<?> saveUserData(@RequestBody User user) throws UserAlreadyRegistered {
        try {
           return new ResponseEntity(service.saveuserdeatilstodatabase(user), HttpStatus.CREATED);
        }
        catch (UserAlreadyRegistered u)
        {
            throw new UserAlreadyRegistered();
        } catch (Exception e) {
            return new ResponseEntity("Internal server Error ...pls Try Later", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //checked
    @PostMapping("/loginUser")
    @HystrixCommand(fallbackMethod = "fallBackUserLogin",commandKey = "loginKry",groupKey = "login")
    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "7000")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException, InterruptedException {
        User userObj = service.findByUserEmailIdAndUserPassword(user.getUserEmailId(), user.getUserPassword());
        Thread.sleep(70000);
        Map<String, String> map = null;
        try {

            if (userObj!=null) {
                map =token.generateToken(user);
            }
            emailServiceAuth.loginUser(user);
            System.out.println("Mail send");
            responseEntity = new ResponseEntity<>(map, HttpStatus.OK);
            System.out.println("Login SuccesFully");

        } catch (Exception e) {

            responseEntity = new ResponseEntity("Server Down pls Take Some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//Checked
    @PutMapping("/updateProfile/{userEmailId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String userEmailId,@RequestBody User user) throws UserNotFoundException
    {
        System.out.println("Check For Update ");
        try {
                System.out.println("User Find ");
                responseEntity = new ResponseEntity(service.updateUser(userEmailId, user), HttpStatus.CREATED);
                System.out.println("User update");
        }catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found Exception",HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity("Check Url Its Not Correct...",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//checked
    @GetMapping("/resetPassword/{userEmailId}")
    ResponseEntity<?> resetPassword(@PathVariable String userEmailId,User user) throws UserNotFoundException
    {
        System.out.println(userEmailId+"**********************************");
        try
        {
            emailServiceAuth.ResetPasswords(user);
            responseEntity=new ResponseEntity(service.resetPassword(userEmailId,user),HttpStatus.CREATED);
        }catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found Exception ",HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity("Internal Server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    ResponseEntity<?> fallBackUserLogin(User user){
        return new ResponseEntity<>("login fail try after some time",HttpStatus.GATEWAY_TIMEOUT);
    }

    @PostMapping("/feedback/{userEmailId}/{comment}")
    ResponseEntity<?> feedBack(@PathVariable String userEmailId,@PathVariable String comment)
    {
        System.out.println("In feed Back Service");
        try
        {
            emailServiceAuth.feedBack(userEmailId, comment);
            System.out.println("mail send");
            responseEntity=new ResponseEntity(service.feedbackForm(userEmailId, comment),HttpStatus.OK);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return responseEntity;
    }
}

