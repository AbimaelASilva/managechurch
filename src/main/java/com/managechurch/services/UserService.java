package com.managechurch.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managechurch.dto.ResponseDTO;
import com.managechurch.dto.UserDTO;
import com.managechurch.entities.UserEntity;
import com.managechurch.mapper.UserMapper;
import com.managechurch.repositories.UserRepository;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    final String sucess = "SUCCESS";

    public ResponseDTO create(UserDTO user) {
        try {
            UserEntity userEntityToSave = new UserEntity(user);
            UserEntity userEntitySaved = userRepository.save(userEntityToSave);
            return new ResponseDTO(sucess, userEntitySaved);
        } catch (DataIntegrityViolationException e) {
            throw new DataItegrityException(
                    "Não foi possível salvar a informação, certifique-se de que informou todos os dados corretamente.");
        }
    }

    public ResponseDTO findById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o usuario com o id: " + id));
        return new ResponseDTO("SUCCESS", userEntity);
    }

    public ResponseDTO findBy(String type, String value) {
        try {
            UserEntity userEntity;
            if (type.equals("LOGIN")) {
                userEntity = userRepository.findByLogin(value);
            } else {
                userEntity = userRepository.findByName(value);
            }

            return new ResponseDTO(sucess, userEntity);
        } catch (Exception e) {
            throw new EntityNotFoundException("Não foi encontrado o usuario com o " + type + "  " + value);
        }
    }

    public ResponseDTO findAll() {
        List<UserEntity> userEntity = userRepository.findAll();
        List<UserDTO> list = new UserEntity().convertToDtoList(userEntity);
        return new ResponseDTO(sucess, list);
    }

    // Tutorial abrodando o uso da "Anotação @Mapper"
    // https://www.baeldung.com/spring-data-partial-update
    public ResponseDTO update(UserDTO user) {

        Optional<UserEntity> optionalUser = userRepository.findById(user.getId());

        if (optionalUser.isPresent()) {
            UserEntity userEntityToSave = optionalUser.get();
            userMapper.updateUserFromDto(user, userEntityToSave);
            UserEntity savedUser = userRepository.save(userEntityToSave);
            return new ResponseDTO(sucess, savedUser);
        } else {
            throw new DataItegrityException(
                    "Não foi possível atualizar os dados do usuário, certifique-se de que informou todos os dados corretamente.");
        }

    }

}
