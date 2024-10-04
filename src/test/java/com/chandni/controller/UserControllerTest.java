package com.chandni.controller;
import com.chandni.api.controller.UserController;
import com.chandni.api.model.User;
import com.chandni.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    User user1 = new User(1, "Alby", 32, "alby@abc.com");
    User user2 = new User(1, "Molly", 25, "molly@abc.com");
    User user3 = new User(1, "Shae", 42, "shae@abc.com");
    List<User> mockUsers = new ArrayList<>(Arrays.asList(user1, user2, user3));

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        // this wont require any Tomcat server to be setup/run
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
    }

    @Test
    public void getUserByName() throws Exception {

        Mockito.when(userService.getUser(user1.getName())).thenReturn(user1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/getUser?name={Molly}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void getAllUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getAllUsers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mockUsers").exists());
    }

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content("5, UserFive, 5, user5@mail.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/updateUser")
                .content("{\"id\":100,\"name\":\"Shae\",\"age\":5,\"email\":\"shae@mail.com\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("100"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Shae"));
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteUser{userName=Al}"))
                .andExpect(status().isAccepted());
    }
}
