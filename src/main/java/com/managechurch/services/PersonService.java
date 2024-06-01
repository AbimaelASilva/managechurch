package com.managechurch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.PersonDTO;
import com.managechurch.entities.PersonEntity;
import com.managechurch.mapper.PersonMapper;
import com.managechurch.repositories.PersonRepository;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    final String success = "SUCCESS";

    public ResponseDTO create(PersonDTO person) {
        try {
            PersonEntity personEntityToSave = new PersonEntity(person);
            PersonEntity personEntitySaved = personRepository.save(personEntityToSave);
            return new ResponseDTO(success, personEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DataItegrityException();
        }
    }

    public ResponseDTO findById(Integer id) {
        PersonEntity personEntity = personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrada a pessoa com o id: " + id));
        return new ResponseDTO(success, personEntity);
    }

    public ResponseDTO findBy(String type, String value) {
        try {
            PersonEntity personEntity;
            if (type.equals("EMAIL")) {
                personEntity = personRepository.findByEmail(value);
            } else {
                personEntity = personRepository.findByName(value);
            }

            return new ResponseDTO(success, personEntity);
        } catch (Exception e) {
            throw new EntityNotFoundException("Não foi encontrada a pessoa com o " + type + " " + value);
        }
    }

    public ResponseDTO findAll() {
        List<PersonEntity> personEntities = personRepository.findAll();
        List<PersonDTO> list =  new PersonEntity().convertToDtoList(personEntities);
        return new ResponseDTO(success, list);
    }

    public ResponseDTO update(PersonDTO person) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(person.getId());

        if (optionalPerson.isPresent()) {
            PersonEntity personEntityToSave = optionalPerson.get();
            personMapper.updateEntityFromDto(person, personEntityToSave);
            PersonEntity savedPerson = personRepository.save(personEntityToSave);
            return new ResponseDTO(success, savedPerson);
        } else {
            throw new DataItegrityException(
                    "Não foi possível atualizar os dados da pessoa, certifique-se de que informou todos os dados corretamente.");
        }
    }

    public ResponseDTO delete(Integer id) {
        try {
            personRepository.deleteById(id);
            return new ResponseDTO(success, "Pessoa deletada com sucesso!");
        } catch (Exception e) {
            return new ResponseDTO("ERROR",
                    new EntityNotFoundException("Não foi possível deletar a pessoa com o id: " + id));
        }
    }
}
