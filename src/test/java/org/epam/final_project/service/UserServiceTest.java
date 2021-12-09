package org.epam.final_project.service;

import org.epam.final_project.model.Info;
import org.epam.final_project.model.User;
import org.epam.final_project.model.enums.Role;
import org.epam.final_project.reposetories.InfoRepository;
import org.epam.final_project.reposetories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private InfoRepository infoRepository;
    @InjectMocks
    private UserService userService;

public UserServiceTest() {
    userRepository=Mockito.mock(UserRepository.class);
}
    @Test
    public void addUserTest() {
        User user=new User("prehot2002@gmail.com","1", Role.ENTRANT);
        Mockito.when(userRepository.existsByEmail("prehot2002@gmail.com")).thenReturn(true);
        Assertions.assertNull(userService.addUser(user));
    }


}