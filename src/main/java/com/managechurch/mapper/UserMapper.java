package com.managechurch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.managechurch.dto.UserDTO;
import com.managechurch.entities.UserEntity;


//Esta classe mapeia o DTO para o Entity, tratando os campos nulos para não dar erro na hora de salvar no banco de dados
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    void updateEntityFromDto(UserDTO dto, @MappingTarget UserEntity entity);
}