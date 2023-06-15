import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.Group8.ToDo.Authentication.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Authentication.exception.UserNotFoundException;
import com.Group8.ToDo.Authentication.model.User;
import com.Group8.ToDo.Authentication.repository.UserRepository;
import com.Group8.ToDo.Authentication.service.UserServiesImpl;
import lombok.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(MockitoExtension.class)
public class ServiceTestCase {

    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiesImpl userService;
    private User user;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        user = new User("rohitsoni@gmail.com", "Rohit", "Rohit@123");
        mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
    }

    @AfterEach
    public void tearDown()
    {
        user = null;
    }

    @Test
    public void givenUserToSave() throws UserAlreadyRegistered {
        when(userRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(null));
        assertEquals(user,userService.saveuserdeatilstodatabase(user));
        verify(userRepository,times(1)).findById(user.getUserEmailId());
        verify(userRepository,times(1)).save(user);

    }


    @Test
    public void givenUserToSaveUserFailure(){
        when(userRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(user));
        assertThrows(UserAlreadyRegistered.class,()->userService.saveuserdeatilstodatabase(user));
        verify(userRepository,times(1)).findById(user.getUserEmailId());
        verify(userRepository,times(0)).save(user);

    }
    @Test
    public void givenUserToLoginUser() throws UserNotFoundException {
        when(userRepository.findByUserEmailIdAndUserPassword(user.getUserEmailId(),user.getUserPassword())).thenReturn(user);
        assertEquals(user,userService.findByUserEmailIdAndUserPassword(user.getUserEmailId(),user.getUserPassword()));
        verify(userRepository,times(1)).findByUserEmailIdAndUserPassword(user.getUserEmailId(),user.getUserPassword());
    }
    @Test
    public void givenUserToLoginUserFailure() throws UserNotFoundException {
        when(userRepository.findByUserEmailIdAndUserPassword(user.getUserEmailId(),user.getUserPassword())).thenReturn(null);
        assertThrows(UserNotFoundException.class,()->userService.findByUserEmailIdAndUserPassword(user.getUserEmailId(),user.getUserPassword()));
        verify(userRepository,times(1)).findByUserEmailIdAndUserPassword(user.getUserEmailId(),user.getUserPassword());
    }
//    @Test
//    public void givenUserToUpdateUser() throws UserNotFoundException, UserAlreadyRegistered {
//        when(userRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(null));
//        user.setUserName("Rajesh");
//        user.setUserPassword("Rohit@321");
//        userService.saveuserdeatilstodatabase(user);
//        assertEquals(user,userService.updateUser(user.getUserEmailId(),user));
//        verify(userRepository,times(2)).findById(user.getUserEmailId());
//        verify(userRepository,times(1)).save(user);
//    }
//    @Test
//    public void givenUserToUpdateUserFailure(){
//        when(userRepository.findById(user.getUserEmailId())).thenReturn(Optional.ofNullable(user));
//        user.setUserName("Rajesh");
//        user.setUserPassword("Rohit@321");
//        assertThrows(UserAlreadyRegistered.class,()->userService.updateUser(user.getUserEmailId(),user));
//        verify(userRepository,times(1)).findById(user.getUserEmailId());
//        verify(userRepository,times(0)).save(user);
//    }
//    @Test
//    public void resetPassword() throws UserNotFoundException, UserAlreadyRegistered {
//        when(userRepository.findByUserEmailIdAndUserName(user.getUserEmailId(),user.getUserName())).thenReturn(user);
//        user.setUserPassword("Rohit456@");
//        assertEquals(user,userService.resetPassword(user.getUserEmailId(), user.getUserName(),user));
//        verify(userRepository,times(1)).findByUserEmailIdAndUserName(user.getUserEmailId(),user.getUserName());
//        when(userRepository.findByUserEmailIdAndUserName(user.getUserEmailId(), user.getUserName())).thenReturn(user);
//        user.setUserPassword("Rohit123@");
////        userService.saveuserdeatilstodatabase(user);
//        assertEquals(user,userRepository.findByUserEmailIdAndUserName(user.getUserName(), user.getUserPassword()));
////        assertThrows(UserAlreadyRegistered.class,()->userService.resetPassword(user.getUserEmailId(),user.getUserName(),user));
//        verify(userRepository,times(1)).findByUserEmailIdAndUserName(user.getUserEmailId(), user.getUserName());
//        verify(userRepository,times(0)).save(user);
    }


