package com.managechurch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managechurch.dto.PeriodWorshipDTO;
import com.managechurch.dto.ResponseDTO;
import com.managechurch.entities.PeriodWorshipEntity;
import com.managechurch.helpers.enums.ResponseStatusEnum;
import com.managechurch.mapper.PeriodWorshipMapper;
import com.managechurch.repositories.PeriodWorshipRepository;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

@Service
public class PeriodWorshipService {

    @Autowired
    private PeriodWorshipRepository periodRepository;

    @Autowired
    private PeriodWorshipMapper itemMapper;

    public ResponseDTO create(PeriodWorshipDTO periodWorshipDTO) {
        try {
            PeriodWorshipEntity periodEntityToSave = new PeriodWorshipEntity(periodWorshipDTO);
            PeriodWorshipEntity periodEntitySaved = periodRepository.save(periodEntityToSave);
            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), periodEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DataItegrityException();
        }
    }

    public ResponseDTO findById(Integer id) {
        PeriodWorshipEntity periodEntity = periodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o item com o id: " + id));
        return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), periodEntity);
    }

    public ResponseDTO findBy(String type, String value) {
        try {
            PeriodWorshipEntity periodWorshipEntity;
            if (type.equals("NAME")) {
                periodWorshipEntity = periodRepository.findByName(value);
            } else {
                throw new EntityNotFoundException("Tipo de busca não suportado: " + type);
            }
            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), periodWorshipEntity);
        } catch (Exception e) {
            throw new EntityNotFoundException("Não foi encontrado o item com o " + type + " " + value);
        }
    }

    public ResponseDTO findAll() {
        List<PeriodWorshipEntity> periodEntities = periodRepository.findAll();
        List<PeriodWorshipDTO> list = new PeriodWorshipEntity().convertPeriodToDtoList(periodEntities);
        return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), list);
    }

    // Tutorial abordando o uso da "Anotação @Mapper"
    // https://www.baeldung.com/spring-data-partial-update
    public ResponseDTO update(PeriodWorshipDTO periodWorshipDTO) {
        Optional<PeriodWorshipEntity> optionalList = periodRepository.findById(periodWorshipDTO.getId());
        if (optionalList.isPresent()) {
            PeriodWorshipEntity periodEntityToSave = optionalList.get();
            itemMapper.updateEntityFromDto(periodWorshipDTO, periodEntityToSave);
            PeriodWorshipEntity savedItem = periodRepository.save(periodEntityToSave);
            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), savedItem);
        } else {
            throw new DataItegrityException(
                    "Não foi possível atualizar os dados do item, certifique-se de que informou todos os dados corretamente.");
        }
    }

    public ResponseDTO delete(Integer id) {
        try {
            periodRepository.deleteById(id);
            return new ResponseDTO(ResponseStatusEnum.SUCCESS.name(), "Item deletado com sucesso!");
        } catch (Exception e) {
            return new ResponseDTO("ERROR",
                    new EntityNotFoundException("Não foi possível deletar o item com o id: " + id));
        }
    }
}
