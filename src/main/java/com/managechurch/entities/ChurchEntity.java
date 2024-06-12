package com.managechurch.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // Aqui o nome do mappedBy tem que ser o mesmo nome da propriedade da
    // ChurchEntity PersonEntity
    @OneToMany(mappedBy = "church", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // Aqui poderia ser uma List<PersonEntity> persons ... ver explicação
    // https://www.youtube.com/watch?v=Ca30sv9EbLo&t=2271s
    private List<PersonEntity> persons;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "church", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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
        // this.worships = this.convertDtoToEntitList(churchDTO.getWorships());
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

    public List<PersonEntity> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonEntity> persons) {
        this.persons = persons;
    }

}
