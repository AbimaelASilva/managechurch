package com.managechurch.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.ItemWorshipDTO;
import com.managechurch.services.ItemWorshipService;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/itemWorship", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemWorshipController {

    @Autowired
    private ItemWorshipService itemWorshipService;

    @PostMapping
    public ResponseEntity<ResponseDTO> create(@RequestBody ItemWorshipDTO itemWorshipDTO) {
        ResponseDTO dto = itemWorshipService.create(itemWorshipDTO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<ResponseDTO> getById(@PathVariable("id") Integer id) {
        ResponseDTO dto = itemWorshipService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping()
    public ResponseEntity<ResponseDTO> getAll() {
        ResponseDTO itemWorshipDTOList = itemWorshipService.findAll();
        return ResponseEntity.ok().body(itemWorshipDTOList);
    }

    @GetMapping("/findMinistry") // achamada do EP deve ser assim: /users/findUser?type=LOGIN&value=abimael
    public ResponseEntity<ResponseDTO> findMinistry(@RequestParam("type") String type,
            @RequestParam("value") String value) { // tyes = // LOGIN, NAME
        ResponseDTO dto = itemWorshipService.findBy(type, value);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody ItemWorshipDTO itemWorshipDTO) {
        ResponseDTO dto = itemWorshipService.update(itemWorshipDTO);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}") // achamada do EP deve ser assim: // /users/5
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Integer id) {
        ResponseDTO dto = itemWorshipService.delete(id);
        return ResponseEntity.ok().body(dto);
    }

}
