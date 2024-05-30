package com.managechurch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.managechurch.dto.UserDTO;
import com.managechurch.entities.UserEntity;
import com.managechurch.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    // https://ppbruna.medium.com/um-guia-para-tratamento-de-erros-em-apis-spring-7c2dee62d1b6

    @PostMapping
    public ResponseEntity<UserDTO>  create(@RequestBody UserDTO userDAO) {
        UserDTO user = userService.create(userDAO);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<UserDTO> getById(@PathVariable("id") Integer id) {
        UserDTO user = userService.findById(id);
       // return new ResponseEntity<>(user, HttpStatus.OK);
        return  ResponseEntity.ok().body(user);
    }

    // @GetMapping()
    // public ResponseEntity<List<UserDTO>> getAll() {
    // return new ResponseEntity<>(userFacade.getAll(), HttpStatus.OK);
    // }

    // @GetMapping("/findUser") // achamada do EP deve ser assim:
    // /users/findUser?type=LOGIN&value=abimael
    // public ResponseEntity findUser(@RequestParam("type") String type,
    // @RequestParam("value") String value) { // tyes = // LOGIN,
    // UserDTO user = userFacade.findUser(type, value);

    // return new ResponseEntity<>(user, HttpStatus.OK);
    // }

}
