package com.managechurch.dto;

import com.managechurch.entities.MinistryEntity;

public class MinistryDTO {

    //Criei o body da requisição POST para insominica com as propiedades e valores ficticios:
    private Integer id;

    private String name;

    private String description;

    public MinistryDTO() {
    }

    public MinistryDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    //https://www.youtube.com/watch?v=ninjPTQjNSI
    //No minuto 7:25 tem exemplo de como converter uma entidade para um DTO. O Spring Boot faz isso automaticamente
    public MinistryDTO(MinistryEntity ministryEntity) {
        this.id = ministryEntity.getId();
        this.name = ministryEntity.getName();
        this.description = ministryEntity.getDescription();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
