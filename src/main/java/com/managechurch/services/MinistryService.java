package com.managechurch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.MinistryDTO;
import com.managechurch.entities.MinistryEntity;
import com.managechurch.helpers.ResponseStatusEnum;
import com.managechurch.mapper.MinistryMapper;
import com.managechurch.repositories.MinistryRepository;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

@Service
public class MinistryService {

    @Autowired
    private MinistryRepository ministryRepository;

    @Autowired
    private MinistryMapper mapperDtoEntity;

    public ResponseDTO create(MinistryDTO ministry) {
        try {
            MinistryEntity ministryEntityToSave = new MinistryEntity(ministry);
            MinistryEntity ministryEntitySaved = ministryRepository.save(ministryEntityToSave);
            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), ministryEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DataItegrityException();
        }
    }

    public ResponseDTO findById(Integer id) {
        MinistryEntity ministryEntity = ministryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o ministério com o id: " + id));
        return new ResponseDTO("SUCCESS", ministryEntity);
    }

    public ResponseDTO findBy(String type, String value) {
        try {
            MinistryEntity ministryEntity = ministryRepository.findByName(value);

            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), ministryEntity);
        } catch (Exception e) {
            throw new EntityNotFoundException("Não foi encontrado o ministério com o " + type + "  " + value);
        }
    }

    public ResponseDTO findAll() {
        List<MinistryEntity> ministryEntities = ministryRepository.findAll();
        List<MinistryDTO> list = new MinistryEntity().convertToDtoList(ministryEntities);
        return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), list);
    }

    // Tutorial abrodando o uso da "Anotação @Mapper"
    // https://www.baeldung.com/spring-data-partial-update
    public ResponseDTO update(MinistryDTO ministry) {

        Optional<MinistryEntity> optionalMinistry = ministryRepository.findById(ministry.getId());
        if (optionalMinistry.isPresent()) {
            MinistryEntity ministryEntityToSave = optionalMinistry.get();
            mapperDtoEntity.updateEntityFromDto(ministry, ministryEntityToSave);
            MinistryEntity savedMinistry = ministryRepository.save(ministryEntityToSave);
            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), savedMinistry);
        } else {
            throw new DataItegrityException(
                    "Não foi possível atualizar os dados do Ministério, certifique-se de que informou todos os dados corretamente.");
        }
    }

    public ResponseDTO delete(Integer id) {
        try {
            ministryRepository.deleteById(id);
            return new ResponseDTO("SUCCESS", "Ministério deletado com ResponseStatusEnum.SUCCESS.name()o!");
        } catch (Exception e) {
            return new ResponseDTO("ERROR",
                    new EntityNotFoundException("Não foi possível deletar o ministério com o id: " + id));
        }
    }

}
