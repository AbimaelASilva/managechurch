package com.managechurch.dto;

import java.util.Date;

import com.managechurch.entities.PersonEntity;
import com.managechurch.helpers.GenderEnum;

public class PersonDTO {

    //Criei o body da requisição POST para insominica com as propiedades e valores ficticios:

    private Integer id;

    private String name;

    private Date birthDate;

    private GenderEnum gender;

    private String phone;

    private String email;

    private Boolean isActive;

    public PersonDTO() {
    }

    public PersonDTO(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.name = personEntity.getName();
        this.birthDate = personEntity.getBirthDate();
        this.gender = personEntity.getGender();
        this.phone = personEntity.getPhone();
        this.email = personEntity.getEmail();
        this.isActive = personEntity.getIsActive();
    }

    // Getters and Setters
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public GenderEnum getGender() {
        return gender;
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
}
