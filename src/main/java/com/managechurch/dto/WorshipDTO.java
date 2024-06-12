package com.managechurch.dto;

import java.util.Date;
import java.util.List;


import com.managechurch.entities.WorshipEntity;

public class WorshipDTO {
    private Integer id;
    private String name;
    private Date date;
    private Integer churchId;
    private List<PeriodWorshipDTO> periods;

    // Construtores
    public WorshipDTO() {
    }

    public WorshipDTO(Integer id, String name, Date date, Integer churchId, List<PeriodWorshipDTO> periods) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.churchId = churchId;
        this.periods = periods;
    }

    //https://www.youtube.com/watch?v=ninjPTQjNSI
    //No minuto 7:25 tem exemplo de como converter uma entidade para um DTO. O Spring Boot faz isso automaticamente
    public WorshipDTO(WorshipEntity worshipEntity) {
        this.id = worshipEntity.getId();
        this.name = worshipEntity.getName();
        this.date = worshipEntity.getDate();
        this.churchId = worshipEntity.getId();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getChurchId() {
        return churchId;
    }

    public void setChurchId(Integer churchId) {
        this.churchId = churchId;
    }

    public List<PeriodWorshipDTO> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodWorshipDTO> periods) {
        this.periods = periods;
    }
}
