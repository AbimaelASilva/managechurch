package com.managechurch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.managechurch.dto.PeriodWorshipDTO;
import com.managechurch.entities.PeriodWorshipEntity;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PeriodWorshipMapper {

    @Mapping(target = "items", source = "dto.itemsWorship")
    void updateEntityFromDto(PeriodWorshipDTO dto, @MappingTarget PeriodWorshipEntity entity);
}