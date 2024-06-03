package com.managechurch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.managechurch.dto.ChurchDTO;
import com.managechurch.entities.ChurchEntity;


//Esta classe mapeia o DTO para o Entity, tratando os campos nulos para não dar erro na hora de salvar no banco de dados
//@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChurchMapper {

     @Mapping(target = "worships", source = "worships", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChurchDTO dto, @MappingTarget ChurchEntity entity);
}