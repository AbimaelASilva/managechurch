package com.managechurch.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.DynamicInsert;

import com.managechurch.dto.ItemWorshipDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@DynamicInsert
@Table(name = "items_worship")
public class ItemWorshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 60, unique = true, nullable = false)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "periods_worship_id", nullable = false)
    private PeriodWorshipEntity periodWorship;

    public ItemWorshipEntity() {
    }

    public ItemWorshipEntity(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ItemWorshipEntity(ItemWorshipDTO itemDTO) {
        this.id = itemDTO.getId();
        this.name = itemDTO.getName();
        this.description = itemDTO.getDescription();

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

    public List<ItemWorshipDTO> convertToDtoList(List<ItemWorshipEntity> itemWorshipEntity) {

        List<ItemWorshipDTO> itemDTO = itemWorshipEntity.stream()
                // .map(ItemEntity -> new MinistryDTO(ItemEntity)) ==
                // .map(MinistryDTO::new)
                .map(ItemWorshipDTO::new).collect(Collectors.toList());

        return itemDTO;

    }

}
