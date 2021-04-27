package com.example.practice15.services;

import com.example.practice15.models.User;
import com.example.practice15.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDetailServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> captor;
    private UserDetailServiceImpl userDetailService;

    @Autowired
    public void setUserDetailService(UserDetailServiceImpl userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("user");
        userDetailService.saveUser(user,"some cookie");

        verify(userRepository).saveAndFlush(captor.capture());
        User captured = captor.getValue();
        Assertions.assertEquals(user.getEmail(), captured.getEmail());
        Assertions.assertEquals(user.getPassword(), captured.getPassword());
        Assertions.assertEquals(user.getSessionId(), captured.getSessionId());
    }
}
