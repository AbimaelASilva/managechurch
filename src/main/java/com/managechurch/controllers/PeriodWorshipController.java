package com.managechurch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.PeriodWorshipDTO;
import com.managechurch.services.PeriodWorshipService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/periodWorship", produces = MediaType.APPLICATION_JSON_VALUE)
public class PeriodWorshipController {

    @Autowired
    private PeriodWorshipService periodWorshipService;

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody PeriodWorshipDTO periodWorshipDTO) {
        ResponseDTO dto = periodWorshipService.create(periodWorshipDTO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        ResponseDTO dto = periodWorshipService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO periodWorshipDTOList = periodWorshipService.findAll();
        return ResponseEntity.ok().body(periodWorshipDTOList);
    }

    @GetMapping("/find") // achamada do EP deve ser assim: /users/findUser?type=LOGIN&value=abimael
    public ResponseEntity<ResponseDTO> findMinistry(@RequestParam("type") String type,
            @RequestParam("value") String value) { // tyes = // LOGIN, NAME
        ResponseDTO dto = periodWorshipService.findBy(type, value);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody PeriodWorshipDTO periodWorshipDTO) {
        ResponseDTO dto = periodWorshipService.update(periodWorshipDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        ResponseDTO dto = periodWorshipService.delete(id);
        return ResponseEntity.ok().body(dto);
    }

}
