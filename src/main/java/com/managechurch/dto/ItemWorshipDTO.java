package com.managechurch.dto;

import com.managechurch.entities.ItemWorshipEntity;

public class ItemWorshipDTO {

    private Integer id;

    private String name;

    private String description;

    public ItemWorshipDTO() {
    }

    public ItemWorshipDTO(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ItemWorshipDTO(ItemWorshipEntity itensWorshipEntity) {
        this.id = itensWorshipEntity.getId();
        this.name = itensWorshipEntity.getName();
        this.description = itensWorshipEntity.getDescription();
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
