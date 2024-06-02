package com.managechurch.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.DynamicInsert;

import com.managechurch.dto.ItemWorshipDTO;
import com.managechurch.dto.PeriodWorshipDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@DynamicInsert
@Table(name = "periods_worship")
public class PeriodWorshipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 60, unique = true, nullable = false)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemWorshipEntity> itemsWorship;

    public PeriodWorshipEntity() {
    }

    public PeriodWorshipEntity(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PeriodWorshipEntity(PeriodWorshipDTO periodWorshipDTO) {
        this.id = periodWorshipDTO.getId();
        this.name = periodWorshipDTO.getName();
        this.description = periodWorshipDTO.getDescription();
        this.itemsWorship = convertDtoToEntityList(periodWorshipDTO.getItemsWorship()) ;

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

    public List<ItemWorshipEntity> getItems() {
        return itemsWorship;
    }

    public void setItems(List<ItemWorshipEntity> items) {
        this.itemsWorship = items;
    }

    public List<PeriodWorshipDTO> convertPeriodToDtoList(List<PeriodWorshipEntity> list) {
        List<PeriodWorshipDTO> ministryDtos = list.stream()
                .map(PeriodWorshipDTO::new).collect(Collectors.toList());
        return ministryDtos;
    }

    public List<ItemWorshipDTO> convertItemsToDtoList(List<ItemWorshipEntity> list) {
        List<ItemWorshipDTO> itemsDtos = list.stream()
                .map(ItemWorshipDTO::new).collect(Collectors.toList());
        return itemsDtos;
    }

    public List<ItemWorshipEntity> convertDtoToEntityList(List<ItemWorshipDTO> list) {
        List<ItemWorshipEntity> itemsDtos = list.stream()
                .map(ItemWorshipEntity::new).collect(Collectors.toList());
        return itemsDtos;
    }

}
