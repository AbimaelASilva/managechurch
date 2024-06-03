package com.managechurch.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.DynamicInsert;

import com.managechurch.dto.PersonDTO;
import com.managechurch.helpers.enums.GenderEnum;
import com.managechurch.helpers.enums.PersonStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@DynamicInsert
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 6, nullable = false)
    private GenderEnum gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 6, nullable = false, columnDefinition = "varchar(15) default 'VISITOR'")
    private PersonStatusEnum personStatusEnum;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public PersonEntity() {
    }

    public PersonEntity(PersonDTO personDTO) {
        this.id = personDTO.getId();
        this.name = personDTO.getName();
        this.birthDate = personDTO.getBirthDate();
        this.gender = personDTO.getGender();
        this.phone = personDTO.getPhone();
        this.email = personDTO.getEmail();
        this.isActive = personDTO.getIsActive();
        this.personStatusEnum = personDTO.getPersonStatusEnum();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer personId) {
        this.id = personId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public GenderEnum getGender() {
        return this.gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<PersonDTO> convertToDtoList(List<PersonEntity> personEntityList) {

        List<PersonDTO> personDtos = personEntityList.stream()
                .map(userEntity -> new PersonDTO(userEntity))
                .collect(Collectors.toList());

        return personDtos;

    }

    public PersonStatusEnum getPersonStatusEnum() {
        return personStatusEnum;
    }

    public void setPersonStatusEnum(PersonStatusEnum personStatusEnum) {
        this.personStatusEnum = personStatusEnum;
    }

}
