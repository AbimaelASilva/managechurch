package com.managechurch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.UserDTO;
import com.managechurch.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody UserDTO userDAO) {
        ResponseDTO dto = userService.create(userDAO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        ResponseDTO dto = userService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO usersList = userService.findAll();
        return ResponseEntity.ok().body(usersList);
    }

    @GetMapping("/findUser") // achamada do EP deve ser assim: /users/findUser?type=LOGIN&value=abimael
    public ResponseEntity<ResponseDTO> findUser(@RequestParam("type") String type,
            @RequestParam("value") String value) { // tyes = // LOGIN, NAME
        ResponseDTO dto = userService.findBy(type, value);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody UserDTO userDAO) {
        ResponseDTO dto = userService.update(userDAO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        ResponseDTO dto = userService.delete(id);
        return ResponseEntity.ok().body(dto);
    }


}
