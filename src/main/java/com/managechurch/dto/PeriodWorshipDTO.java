package com.managechurch.dto;

import java.util.List;

import com.managechurch.entities.PeriodWorshipEntity;

public class PeriodWorshipDTO {

    private Integer id;
    private String name;
    private String description;
    private List<ItemWorshipDTO> itemsWorship;

    
    public PeriodWorshipDTO() {
    }

    public PeriodWorshipDTO(Integer id, String name, String description, List<ItemWorshipDTO> itemsWorship) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.itemsWorship = itemsWorship;
    }

    //https://www.youtube.com/watch?v=ninjPTQjNSI
    //No minuto 7:25 tem exemplo de como converter uma entidade para um DTO. O Spring Boot faz isso automaticamente
    public PeriodWorshipDTO(PeriodWorshipEntity periodEntity) {
        this.id = periodEntity.getId();
        this.name = periodEntity.getName();
        this.description = periodEntity.getDescription();
       // this.itemsWorship = periodEntity.convertItemsToDtoList(periodEntity.getItems());
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemWorshipDTO> getItemsWorship() {
        return itemsWorship;
    }

    public void setItemsWorship(List<ItemWorshipDTO> itemsWorship) {
        this.itemsWorship = itemsWorship;
    }
}

