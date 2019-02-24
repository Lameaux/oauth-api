package com.euromoby.oauth.controllers;

import com.euromoby.oauth.dtos.CreateUser;
import com.euromoby.oauth.dtos.GetUser;
import com.euromoby.oauth.entities.User;
import com.euromoby.oauth.exceptions.ResourceNotFoundException;
import com.euromoby.oauth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path=UsersController.CONTROLLER_PATH,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    public static final String CONTROLLER_PATH = "/api/users";

    @Value("${application.root}")
    String applicationRoot;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    @ResponseBody
    public GetUser getById(@PathVariable("id") UUID id) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            throw new ResourceNotFoundException();
        }

        return new GetUser(user.get());
   }

    @PostMapping
    @ResponseBody
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUser createUserDto, UriComponentsBuilder ucBuilder) {
        User user = userService.create(createUserDto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(locationFor(user));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    private URI locationFor(User user) {
        try {
            return new URI(applicationRoot + CONTROLLER_PATH + "/" + user.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
