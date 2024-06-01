package com.managechurch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managechurch.dto.ItemWorshipDTO;
import com.managechurch.dto.ResponseDTO;
import com.managechurch.entities.ItemWorshipEntity;
import com.managechurch.mapper.ItemWorshipMapper;
import com.managechurch.repositories.ItemWorshipRepository;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

@Service
public class ItemWorshipService {

    @Autowired
    private ItemWorshipRepository itemRepository;

    @Autowired
    private ItemWorshipMapper itemMapper;

    final String success = "SUCCESS";

    public ResponseDTO create(ItemWorshipDTO item) {
        try {
            ItemWorshipEntity itemEntityToSave = new ItemWorshipEntity(item);
            ItemWorshipEntity itemEntitySaved = itemRepository.save(itemEntityToSave);
            return new ResponseDTO(success, itemEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DataItegrityException();
        }
    }

    public ResponseDTO findById(Integer id) {
        ItemWorshipEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o item com o id: " + id));
        return new ResponseDTO(success, itemEntity);
    }

    public ResponseDTO findBy(String type, String value) {
        try {
            ItemWorshipEntity itemEntity;
            if (type.equals("NAME")) {
                itemEntity = itemRepository.findByName(value);
            } else {
                throw new EntityNotFoundException("Tipo de busca não suportado: " + type);
            }
            return new ResponseDTO(success, itemEntity);
        } catch (Exception e) {
            throw new EntityNotFoundException("Não foi encontrado o item com o " + type + " " + value);
        }
    }

    public ResponseDTO findAll() {
        List<ItemWorshipEntity> itemEntities = itemRepository.findAll();
        List<ItemWorshipDTO> list = new ItemWorshipEntity().convertToDtoList(itemEntities);
        return new ResponseDTO(success, list);
    }

    // Tutorial abordando o uso da "Anotação @Mapper"
    // https://www.baeldung.com/spring-data-partial-update
    public ResponseDTO update(ItemWorshipDTO item) {
        Optional<ItemWorshipEntity> optionalItem = itemRepository.findById(item.getId());
        if (optionalItem.isPresent()) {
            ItemWorshipEntity itemEntityToSave = optionalItem.get();
            itemMapper.updateEntityFromDto(item, itemEntityToSave);
            ItemWorshipEntity savedItem = itemRepository.save(itemEntityToSave);
            return new ResponseDTO(success, savedItem);
        } else {
            throw new DataItegrityException(
                    "Não foi possível atualizar os dados do item, certifique-se de que informou todos os dados corretamente.");
        }
    }

    public ResponseDTO delete(Integer id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseDTO(success, "Item deletado com sucesso!");
        } catch (Exception e) {
            return new ResponseDTO("ERROR",
                    new EntityNotFoundException("Não foi possível deletar o item com o id: " + id));
        }
    }
}