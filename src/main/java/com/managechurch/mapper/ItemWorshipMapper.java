package com.managechurch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.managechurch.dto.ItemWorshipDTO;
import com.managechurch.entities.ItemWorshipEntity;

//Esta classe mapeia o DTO para o Entity, tratando os campos nulos para não dar erro na hora de salvar no banco de dados
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ItemWorshipMapper {

    void updateEntityFromDto(ItemWorshipDTO dto, @MappingTarget ItemWorshipEntity entity);
}