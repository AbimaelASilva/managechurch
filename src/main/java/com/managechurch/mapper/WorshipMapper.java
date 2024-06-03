package com.managechurch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.managechurch.dto.WorshipDTO;
import com.managechurch.entities.WorshipEntity;

//Esta classe mapeia o DTO para o Entity, tratando os campos nulos para não dar erro na hora de salvar no banco de dados
//@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface WorshipMapper {

     @Mapping(target = "periods", source = "periods", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(WorshipDTO dto, @MappingTarget WorshipEntity entity);
}