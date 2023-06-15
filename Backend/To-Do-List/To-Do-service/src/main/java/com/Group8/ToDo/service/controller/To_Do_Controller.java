package com.Group8.ToDo.service.controller;

import com.Group8.ToDo.service.exceeptin.TaskAlreadyExistException;
import com.Group8.ToDo.service.exceeptin.TaskNotFoundException;
import com.Group8.ToDo.service.exceeptin.UserAlreadyRegistered;
import com.Group8.ToDo.service.exceeptin.UserNotFoundException;
import com.Group8.ToDo.service.model.Task;
import com.Group8.ToDo.service.model.User;
import com.Group8.ToDo.service.service.To_Do_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/to-do/toDoService")
public class To_Do_Controller {

    private To_Do_Service service;
    ResponseEntity responseEntity;

@Autowired
    public To_Do_Controller(To_Do_Service service) {
        this.service = service;
    }

    //checked
    @PostMapping("/registerUser")
    ResponseEntity<?>  registerUser(@RequestBody User user)
    {
        System.out.println(user);
        try
        {
            System.out.println("check controller");
            responseEntity=new ResponseEntity(service.registerUser(user), HttpStatus.ACCEPTED);
        }
        catch (UserAlreadyRegistered e)
        {
            responseEntity=new ResponseEntity("User Already Exist ",HttpStatus.CONFLICT);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/feedback/{userEmailId}/{message}")
    ResponseEntity<?>  feedbackForm(@PathVariable String userEmailId,@PathVariable String message)
    {
        try
        {
            System.out.println("check controller for FeedBack");
            responseEntity=new ResponseEntity(service.feedbackForm(userEmailId, message), HttpStatus.OK);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//Checked
    @GetMapping("/getUserName/{userEmailId}")
    ResponseEntity<?> getUserName (@PathVariable String userEmailId) throws UserNotFoundException
    {
        try
        {
            responseEntity=new ResponseEntity(service.getUserName(userEmailId),HttpStatus.OK);
        }catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found Exception",HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("User Having Error With Url pls... Check",HttpStatus.OK);
        }
        return responseEntity;
    }
//checked
    @GetMapping("/getUserDetails/{userEmailId}")
    ResponseEntity<?> getUserDetail(@PathVariable String userEmailId) throws UserNotFoundException
    {
        try
        {
            responseEntity=new ResponseEntity(service.getUserDetails(userEmailId),HttpStatus.OK);
        }catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found Exception",HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("User Having Error With Url pls... Check",HttpStatus.OK);
        }
        return responseEntity;
    }
//checked
    @PutMapping("/updateProfile/{userEmailId}")
    ResponseEntity<?> updateProfile(@PathVariable String userEmailId,@RequestBody User user)
    {

        try
        {
            responseEntity=new ResponseEntity(service.updateProfile(userEmailId, user), HttpStatus.ACCEPTED);
        } catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//Checked
    @GetMapping("/resetPassword/{userEmailId}/{userName}")
    ResponseEntity<?> resetPassword(@PathVariable String userEmailId,@PathVariable String userName,@RequestBody User user) throws UserNotFoundException
    {
        System.out.println("USer check");
        try
        {
            responseEntity=new ResponseEntity(service.forgotPassword(userEmailId, userName, user),HttpStatus.OK);

            System.out.println("User checked");
        } catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found Exception",HttpStatus.NOT_FOUND);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("INternal Server error Pls Check ....",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("/addTask/{userEmailId}")
    ResponseEntity<?> addTaskToUser(@RequestBody Task task,@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.addTaskToUser(task, userEmailId),HttpStatus.CREATED);

        } catch (TaskAlreadyExistException e) {
            responseEntity=new ResponseEntity("TAsk Already Saved In list",HttpStatus.CONFLICT);

        } catch (UserNotFoundException | TaskNotFoundException e) {
           responseEntity=new ResponseEntity("USer Not Found Exception",HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @PutMapping("/updateTask/{userEmailId}")
    ResponseEntity<?>updateTask(@RequestBody Task task,@PathVariable String userEmailId){
    try
    {
        responseEntity=new ResponseEntity(service.updateTask(task, userEmailId),HttpStatus.CREATED);
    }catch (Exception e)
    {
        responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
    }
        return responseEntity;
    }

    @PutMapping("/CompleteTask/{userEmailId}")
    ResponseEntity<?>completeTask(@PathVariable int taskId,@PathVariable String userEmailId){
        try
        {
            responseEntity=new ResponseEntity(service.CompleteTask(taskId, userEmailId),HttpStatus.CREATED);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @DeleteMapping("/deleteAr/{userEmailId}/{taskId}")
    ResponseEntity <?> DeleteTaskByArc(@PathVariable int taskId,@PathVariable String userEmailId) throws UserNotFoundException, TaskNotFoundException
    {
        try
        {
            System.out.println("Delete Ar");
            responseEntity =new ResponseEntity(service.deleteArTask(taskId,userEmailId),HttpStatus.OK);
        }
        catch (Exception e)

        {
            responseEntity=new ResponseEntity("Exception by Server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/deleteDl/{userEmailId}/{taskId}")
    ResponseEntity <?> DeleteTaskByDelete(@PathVariable int taskId,@PathVariable String userEmailId) throws UserNotFoundException, TaskNotFoundException
    {
        try
        {
            responseEntity =new ResponseEntity(service.deleteDlTask(taskId,userEmailId),HttpStatus.OK);
        }
        catch (Exception e)

        {
            responseEntity=new ResponseEntity("Exception by Server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }



    @PutMapping("/remove/{userEmailId}/{taskId}")
    ResponseEntity<?>removeTask(@RequestBody Task task,@PathVariable String userEmailId,@PathVariable int taskId){
        System.out.println("Remove Controller ");
        try
        {
            responseEntity=new ResponseEntity(service.removeTaskToRecycle(task,userEmailId,taskId),HttpStatus.OK);
            System.out.println("shifted");
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/shift/{userEmailId}/{taskId}")
    ResponseEntity<?> shiftTask(@RequestBody Task task,@PathVariable String userEmailId,@PathVariable int taskId)
    {
        System.out.println("Working");
        try
        {
            responseEntity=new ResponseEntity(service.shiftToArchieveList(task, userEmailId, taskId),HttpStatus.ACCEPTED);
        }catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found ",HttpStatus.NOT_FOUND);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Internal server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/reShift/{userEmailId}/{taskId}")
    ResponseEntity<?> reShiftTask(@RequestBody Task task,@PathVariable String userEmailId,@PathVariable int taskId)
    {
        System.out.println("Working");
        try
        {
            responseEntity=new ResponseEntity(service.ReShiftToUserTask(task, userEmailId, taskId),HttpStatus.ACCEPTED);
        }catch (UserNotFoundException u)
        {
            responseEntity=new ResponseEntity("User Not Found ",HttpStatus.NOT_FOUND);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Internal server Error",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/restore/{userEmailId}/{taskId}")
    ResponseEntity<?>restoreTask(@RequestBody Task task,@PathVariable String userEmailId,@PathVariable int taskId){
        System.out.println("Remove Controller ");
        try
        {
            responseEntity=new ResponseEntity(service.restoreTask(task,userEmailId,taskId),HttpStatus.OK);
            System.out.println("shifted");
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Error Pls Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/CompletedTask/{userEmailId}")
    ResponseEntity<?> getAllDeletedTask(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.getCompletedTask(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

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

    @GetMapping("/allArTask/{userEmailId}")
    ResponseEntity<?> getAllArchieveTask(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.getAllArchieveTask(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/allPendingTask/{userEmailId}")
    ResponseEntity<?> getPendingTask(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.getPendingTask(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/allDeletedTask/{userEmailId}")
    ResponseEntity<?> getDeletedTask(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.getAllDeletedTask(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/startDate/{userEmailId}")
    ResponseEntity<?>categorizeByStartDate(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.categorizeByStartDate(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/highPriority/{userEmailId}")
    ResponseEntity<?> categorizeByHighPriority(@PathVariable String userEmailId)
    {
        System.out.println("Check Controller for priority");
        try
        {
            System.out.println("try section");
            responseEntity=new ResponseEntity(service.categorizeByHighPriority(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/lowPriority/{userEmailId}")
    ResponseEntity<?> categorizeByLowPriority(@PathVariable String userEmailId)
    {
        System.out.println("Check Controller for priority");
        try
        {
            System.out.println("try section");
            responseEntity=new ResponseEntity(service.categorizeByLowPriority(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/endDate/{userEmailId}")
    ResponseEntity<?> categorizeByEndDate(@PathVariable String userEmailId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.categorizeByEndDate(userEmailId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/getTask/{userEmailId}/{taskId}")
    ResponseEntity<?> getTask(@PathVariable String userEmailId,@PathVariable int taskId)
    {
        try
        {
            responseEntity=new ResponseEntity(service.getTask(userEmailId,taskId),HttpStatus.OK);
        }catch (Exception e)
        {
            responseEntity=new ResponseEntity("Server Not Found ....Try Again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @GetMapping("/taskNearDue/{userEmailId}")
    public ResponseEntity<?> getTasksWithNearDueDate(@PathVariable String userEmailId) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(service.getTasksWithNearDueDate(userEmailId), HttpStatus.OK);
        } catch(UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todayTask/{userEmailId}")
    public ResponseEntity<?> getTodayTask(@PathVariable String userEmailId) throws UserNotFoundException {
        try {
            return new ResponseEntity<>(service.getTodayTask(userEmailId), HttpStatus.OK);
        } catch(UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
