package com.managechurch.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.DynamicInsert;

import com.managechurch.dto.MinistryDTO;

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

   // @OneToMany(mappedBy = "periodsWorship", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemWorshipEntity> itemsWorshipEntity;


    public PeriodWorshipEntity() {
    }

    public PeriodWorshipEntity(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public PeriodWorshipEntity(MinistryDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.description = userDTO.getDescription();

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
        return itemsWorshipEntity;
    }

    public void setItems(List<ItemWorshipEntity> items) {
        this.itemsWorshipEntity = items;
    }

    public List<MinistryDTO> convertToDtoList(List<MinistryEntity> ministriesEntityList) {

        List<MinistryDTO> ministryDtos = ministriesEntityList.stream()
                // .map(ministryEntity -> new MinistryDTO(ministryEntity)) ==
                // .map(MinistryDTO::new)
                .map(MinistryDTO::new).collect(Collectors.toList());

        return ministryDtos;

    }

}
