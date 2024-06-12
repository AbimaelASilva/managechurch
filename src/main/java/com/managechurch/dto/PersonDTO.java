package com.managechurch.dto;

import java.util.Date;

import com.managechurch.entities.PersonEntity;
import com.managechurch.helpers.enums.GenderEnum;
import com.managechurch.helpers.enums.PersonStatusEnum;

public class PersonDTO {

    //Criei o body da requisição POST para insominica com as propiedades e valores ficticios:

    private Integer id;

    private String name;

    private Date birthDate;

    private GenderEnum gender;

    private PersonStatusEnum personStatusEnum;

    private String phone;

    private String email;

    private Boolean isActive;

    public PersonDTO() {
    }

    //https://www.youtube.com/watch?v=ninjPTQjNSI
    //No minuto 7:25 tem exemplo de como converter uma entidade para um DTO. O Spring Boot faz isso automaticamente
    public PersonDTO(PersonEntity personEntity) {
        this.id = personEntity.getId();
        this.name = personEntity.getName();
        this.birthDate = personEntity.getBirthDate();
       // this.gender = personEntity.getGender();
        this.phone = personEntity.getPhone();
        this.email = personEntity.getEmail();
        this.isActive = personEntity.getIsActive();
        this.personStatusEnum = personEntity.getPersonStatusEnum();
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

    public PersonStatusEnum getPersonStatusEnum() {
        return personStatusEnum;
    }

    public void setPersonStatusEnum(PersonStatusEnum personStatusEnum) {
        this.personStatusEnum = personStatusEnum;
    }
}
