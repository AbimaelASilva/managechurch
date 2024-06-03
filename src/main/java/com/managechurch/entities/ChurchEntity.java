package com.managechurch.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.stream.Collectors;

import com.managechurch.dto.ChurchDTO;
import com.managechurch.dto.WorshipDTO;

@Entity
@Table(name = "churches")
public class ChurchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonEntity> persons;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorshipEntity> worships;

    public ChurchEntity(Integer id, String name, String phone, List<WorshipEntity> worships) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.worships = worships;
    }

    public ChurchEntity(ChurchDTO churchDTO) {
        this.id = churchDTO.getId();
        this.name = churchDTO.getName();
        this.phone = churchDTO.getPhone();
        this.worships = this.convertDtoToEntitList(churchDTO.getWorships());
    }

    public ChurchEntity() {
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

    public List<WorshipEntity> getWorships() {
        return worships;
    }

    public void setWorships(List<WorshipEntity> worships) {
        this.worships = worships;
    }

    public List<WorshipEntity> convertDtoToEntitList(List<WorshipDTO> list) {
        List<WorshipEntity> itemsDtos = list.stream()
                .map(WorshipEntity::new).collect(Collectors.toList());
        return itemsDtos;
    }

}
