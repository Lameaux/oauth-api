package com.euromoby.oauth.controllers;

import com.euromoby.oauth.dtos.CreateUser;
import com.euromoby.oauth.entities.User;
import com.euromoby.oauth.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(UsersController.class)
public class UsersControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${application.root}")
    String applicationRoot;

    @MockBean
    private UserService userService;

    @Test
    public void createUser() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID());

        CreateUser createUser = new CreateUser();
        createUser.setEmail("donald@trump.com");
        createUser.setFirstName("Donald");
        createUser.setLastName("Trump");
        createUser.setPassword("melania");

        when(userService.create(any(CreateUser.class))).thenReturn(user);

        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUser))
        ).andExpect(status().isCreated())
                .andExpect(redirectedUrlTemplate(applicationRoot + UsersController.CONTROLLER_PATH + "/{id}", user.getId()));
    }

}
