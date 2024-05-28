package com.managechurch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managechurch.entities.users.UserDAO;
import com.managechurch.entities.users.UserFacade;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserFacade userFacade;

    // https://ppbruna.medium.com/um-guia-para-tratamento-de-erros-em-apis-spring-7c2dee62d1b6

    @GetMapping()

    public ResponseEntity<List<UserDAO>> getAll() {
        return new ResponseEntity<>(userFacade.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserDAO userDAO) {
        UserDAO user = userFacade.create(userDAO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}") // achamada do EP deve ser assim: // /users/getById/5
    public ResponseEntity getById(@PathVariable("id") Integer id) {
        UserDAO user = userFacade.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUser") // achamada do EP deve ser assim: /users/findUser?type=LOGIN&value=abimael
    public ResponseEntity findUser(@RequestParam("type") String type, @RequestParam("value") String value) { // tyes = // LOGIN,
        UserDAO user = userFacade.findUser(type, value);
                                                                                                             
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
