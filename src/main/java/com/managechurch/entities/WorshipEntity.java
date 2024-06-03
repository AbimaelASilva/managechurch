package com.managechurch.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.managechurch.dto.PeriodWorshipDTO;
import com.managechurch.dto.WorshipDTO;

@Entity
@Table(name = "worship")
public class WorshipEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "church_id")
    private ChurchEntity church;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "worship")
    // O joinColumn é vai gerar lá na tabela PeriodWorship a coluna com nome "worship_id" 
   // @JoinColumn(name = "worship_id")
    private List<PeriodWorshipEntity> periods;

    public WorshipEntity(Integer id, String name, Date date, ChurchEntity church, List<PeriodWorshipEntity> periods) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.church = church;
        this.periods = periods;
    }

    public WorshipEntity(WorshipDTO worshipDTO) {
        this.id = worshipDTO.getId();
        this.name = worshipDTO.getName();
        this.date = worshipDTO.getDate();
        // this.church = worshipDTO.getChurchId();
        this.periods = this.convertDtoToEntityList(worshipDTO.getPeriods());
    }

    public WorshipEntity() {
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

    public ChurchEntity getChurch() {
        return church;
    }

    public void setChurch(ChurchEntity church) {
        this.church = church;
    }

    public List<PeriodWorshipEntity> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodWorshipEntity> periods) {
        this.periods = periods;
    }

    public List<PeriodWorshipEntity> convertDtoToEntityList(List<PeriodWorshipDTO> list) {
        List<PeriodWorshipEntity> itemsDtos = list.stream()
                .map(PeriodWorshipEntity::new).collect(Collectors.toList());
        return itemsDtos;
    }
}
