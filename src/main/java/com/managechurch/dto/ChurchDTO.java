package com.managechurch.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.managechurch.entities.ChurchEntity;
import com.managechurch.entities.WorshipEntity;

public class ChurchDTO {
    private Integer id;
    private String name;
    private String phone;
    private List<WorshipDTO> worships;

    // Construtores
    public ChurchDTO() {
    }

    public ChurchDTO(Integer id, String name, String phone, List<WorshipDTO> worships) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.worships = worships;
    }

    public ChurchDTO(ChurchEntity churchEntity) {
        this.id = churchEntity.getId();
        this.name = churchEntity.getName();
        this.phone = churchEntity.getPhone();
        this.worships = this.convertEntitToDtoList( churchEntity.getWorships());
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<WorshipDTO> getWorships() {
        return worships;
    }

    public void setWorships(List<WorshipDTO> worships) {
        this.worships = worships;
    }

    public List<WorshipDTO> convertEntitToDtoList(List<WorshipEntity> list) {
        List<WorshipDTO> itemsDtos = list.stream()
                .map(WorshipDTO::new).collect(Collectors.toList());
        return itemsDtos;
    }

    
}
