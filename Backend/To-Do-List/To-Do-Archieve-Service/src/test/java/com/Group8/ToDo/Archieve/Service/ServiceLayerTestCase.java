//package com.Group8.ToDo.Archieve.Service;
//
//import com.Group8.ToDo.Archieve.Service.exception.TaskAlreadyExistException;
//import com.Group8.ToDo.Archieve.Service.exception.TaskNotFoundException;
//import com.Group8.ToDo.Archieve.Service.exception.UserAlreadyRegistered;
//import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
//import com.Group8.ToDo.Archieve.Service.model.Task;
//import com.Group8.ToDo.Archieve.Service.model.User;
//import com.Group8.ToDo.Archieve.Service.repository.UserArchieveRepository;
//import com.Group8.ToDo.Archieve.Service.service.UserArchieveServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static com.Group8.ToDo.Archieve.Service.model.Priority.HIGH;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ServiceLayerTestCase {
//    @Mock
//    UserArchieveRepository archiveRepository;
//    @InjectMocks
//    UserArchieveServiceImpl archiveService;
//    private Task task;
//    private User user;
//    List<Task> archieveList;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setUp() throws ParseException {
//
//        task = new Task(1, "Niit", "test", "Check Controller", new Date(), (Date) new SimpleDateFormat("dd/MM/yyyy").parse("08/11/2022"), false, HIGH);
//        user = new User("rohit@gmail.com", "Rohit", "male", (Date) new SimpleDateFormat("dd/MM/yyyy").parse("27/10/1997"), "Rohit@123", archieveList);
//        archieveList = Arrays.asList(task);
//        mockMvc = MockMvcBuilders.standaloneSetup(archiveService).build();
//
//
//    }
//
//    @AfterEach
//    public void resetData() {
//        user = null;
//        task = null;
//    }
//
//    @Test
//    public void givenUserToSaveUser() throws UserAlreadyRegistered {
//        when(archiveRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(null));
//        when(archiveRepository.save(user)).thenReturn(user);
//        assertEquals(user, archiveService.registerToArchieve(user));
//        verify(archiveRepository, times(1)).findById(user.getUserEmailId());
//        verify(archiveRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void givenUserToSaveUserFailure() {
//        when(archiveRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(user));
//        assertThrows(UserAlreadyRegistered.class, () -> archiveService.registerToArchieve(user));
//        verify(archiveRepository, times(1)).findById(user.getUserEmailId());
//        verify(archiveRepository, times(0)).save(user);
//    }
//
////    @Test
////    public void addTaskToUser() throws TaskAlreadyExistException, UserNotFoundException {
////        archieveList = Arrays.asList(task);
////        when(archiveRepository.findByUserEmailId(user.getUserEmailId())).thenReturn(user);
////        when(archiveRepository.save(user)).thenReturn(user);
////        assertEquals(user, archiveService.addTask(task, user.getUserEmailId()));
////        verify(archiveRepository, times(2)).findByUserEmailId(user.getUserEmailId());
////        verify(archiveRepository, times(1)).save(user);
////    }
//
//    @Test
//    public void addTaskToUserFailure() {
//        archieveList = Arrays.asList(task);
//        when(archiveRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(null));
//        assertThrows(UserNotFoundException.class, () -> archiveService.addTask(task, user.getUserEmailId()));
//        verify(archiveRepository, times(1)).findById(user.getUserEmailId());
//        verify(archiveRepository, times(0)).save(user);
//    }
//
////    @Test
////    public void getAllTask() throws TaskNotFoundException, UserNotFoundException {
////        user.setArchieveTask(Arrays.asList(task));
////        when(archiveRepository.findByUserEmailId(user.getUserEmailId())).thenReturn(user);
////        assertEquals(user.getArchieveTask(), archiveService.getAllTask(user.getUserEmailId()));
////        verify(archiveRepository, times(1)).findById(user.getUserEmailId());
////    }
//
//}
//
