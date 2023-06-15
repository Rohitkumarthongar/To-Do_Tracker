//package com.Group8.ToDo.Archieve.Service;
//
//import com.Group8.ToDo.Archieve.Service.controller.ArchieveController;
//import com.Group8.ToDo.Archieve.Service.exception.UserAlreadyRegistered;
//import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
//import com.Group8.ToDo.Archieve.Service.model.Task;
//import com.Group8.ToDo.Archieve.Service.model.User;
//import com.Group8.ToDo.Archieve.Service.service.UserArchieveServiceImpl;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static com.Group8.ToDo.Archieve.Service.model.Priority.HIGH;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class ControllerTest {
//    @ExtendWith(MockitoExtension.class)
//    @Mock
//    private UserArchieveServiceImpl archiveService;
//
//    @InjectMocks
//    private ArchieveController archiveController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//    private Task task;
//    private User user;
//    private List<Task> userList;
//    private List<Task> archieveList;
//    private List<Task> deletedList;
//
//
//
//    @BeforeEach
//    public void setUp() throws ParseException {
//        task=new Task(1,"Niit","test","Check Controller",new Date(), (Date)new SimpleDateFormat("dd/MM/yyyy").parse("08/11/2022"),false ,HIGH);
//        user=new User("Rohit@gmail.com","ROhit Soni","Male",new Date(1997-10-27),"ROhit@123",userList,archieveList,deletedList);
//        archieveList= Arrays.asList(task);
//        mockMvc= MockMvcBuilders.standaloneSetup(archiveController).build();
//    }
//
//    @AfterEach
//    public void tearDown(){
//        user=null;
//        task=null;
//    }
//
//    private  String jsonToString(final Object obj) throws JsonProcessingException {
//        String result=null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonContent = mapper.writeValueAsString(obj);
//            result=jsonContent;
//        }
//        catch (JsonProcessingException e){
//            result="error while conversion";
//        }
//        return result;
//    }
//
//    @Test
//    public void givenUserToSaveReturnUser() throws Exception{
//        when(archiveService.registerToArchieve(any())).thenReturn(user);
//        mockMvc.perform(post("/api/to-do/archieveService/registerUser").contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print()); //actions
//        verify(archiveService,times(1)).registerToArchieve(any());
//    }
//
//    @Test
//    public void givenUserToSaveReturnUserFailure()throws Exception
//    {
//        when(archiveService.registerToArchieve(any())).thenThrow(UserAlreadyRegistered.class);
//        mockMvc.perform(post("/api/to-do/archieveService/registerUser")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(task)))
//                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).registerToArchieve(any());
//    }
//
//    @Test
//    public void givenTaskToAddReturnUser() throws Exception{
//        when(archiveService.addTask(any(),anyString())).thenReturn(user);
//        mockMvc.perform(post("/api/to-do/archieveService/addTask/rohit@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).addTask(any(),anyString());
//    }
//    @Test
//    public void givenTaskToSaveReturnTaskFailure()throws Exception
//    {
//        when(archiveService.addTask(any(),anyString())).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(post("/api/to-do/archieveService/addTask/rohit@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonToString(task)))
//                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).addTask(any(),anyString());
//    }
//
//    @Test
//    public void givenUpdateUser() throws Exception{
//        when(archiveService.updateProfile(anyString(),any())).thenReturn(user);
//        mockMvc.perform(put("/api/to-do/archieveService/updateProfile/rohit@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).updateProfile(anyString(),any());
//    }
//
//    @Test
//    public void givenUpdateUserFail() throws Exception{
//        when(archiveService.updateProfile(anyString(),any())).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(put("/api/to-do/archieveService/updateProfile/roht@gmail.com")
//                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).updateProfile(anyString(),any());
//    }
//
//    @Test
//    public void givenResetPassword() throws Exception {
//        when(archiveService.forgotPassword(anyString(),anyString(),any())).thenReturn(user);
//        mockMvc.perform(put("/api/to-do/archieveService/resetPassword/rohit@gmail.com/rohit")
//                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).forgotPassword(anyString(),anyString(),any());
//    }
//
//    @Test
//    public void givenResetPasswordFail() throws Exception {
//        when(archiveService.forgotPassword(anyString(),anyString(),any())).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(put("/api/to-do/archieveService/resetPassword/rohit@gmail.com/rohit")
//                        .contentType(MediaType.APPLICATION_JSON).content(jsonToString(user)))
//                .andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
//        verify(archiveService,times(1)).forgotPassword(anyString(),anyString(),any());
//    }
//
//
//}
