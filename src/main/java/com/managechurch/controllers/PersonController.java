package com.managechurch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.PersonDTO;
import com.managechurch.services.PersonService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody PersonDTO personDTO) {
        ResponseDTO dto = personService.create(personDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        ResponseDTO dto = personService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO personList = personService.findAll();
        return ResponseEntity.ok().body(personList);
    }

    @GetMapping("/findPerson")
    public ResponseEntity<ResponseDTO> findPerson(@RequestParam("type") String type,
            @RequestParam("value") String value) {
        ResponseDTO dto = personService.findBy(type, value);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody PersonDTO personDTO) {
        ResponseDTO dto = personService.update(personDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        ResponseDTO dto = personService.delete(id);
        return ResponseEntity.ok().body(dto);
    }

}
