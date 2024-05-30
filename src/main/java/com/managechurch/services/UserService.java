package com.managechurch.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.managechurch.dto.UserDTO;
import com.managechurch.entities.UserEntity;
import com.managechurch.repositories.UserRepository;
import com.managechurch.services.exceptions.DataItegrityException;
import com.managechurch.services.exceptions.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO create(UserDTO user) {

        // UserEntity userEntityToSave = new UserEntity(user);
        // UserEntity userEntitySaved = userRepository.save(userEntityToSave)
        // .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o usuario
        // com o id: " + id));

        // return new UserDTO(userEntitySaved);

        try {

            UserEntity userEntityToSave = new UserEntity(user);
            UserEntity userEntitySaved = userRepository.save(userEntityToSave);
            UserDTO userDTO = new UserDTO(userEntitySaved);
            return new UserDTO(userEntitySaved);

        } catch (DataIntegrityViolationException e) {
            throw new DataItegrityException(
                    "Não foi possível salvar a informação, certifique-se de que informou todos os dados corretamente.");
        }
    }

    public UserDTO findById(Integer id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Não foi encontrado o usuario com o id: " + id));

        return new UserDTO(userEntity);

    }

}
