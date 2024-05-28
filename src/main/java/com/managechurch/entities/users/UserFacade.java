package com.managechurch.entities.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserFacade {

    @Autowired
    private UserRepository userRepository;

    public UserDAO getUserById(int id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return convertToDAO(userEntity);
    }

    public UserDAO findUser(String type, String value) {

        UserEntity userEntity;
      

        switch (type) {
            case "LOGIN":
                userEntity = userRepository.findByLogin(value);
                break;
            case "NAME":
            default:
                userEntity = userRepository.findByName(value);

        }

        return convertToDAO(userEntity);
    }

    public List<UserDAO> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::convertToDAO)
                .collect(Collectors.toList());
    }

    public UserDAO create(UserDAO userDAO) {
        UserEntity userEntity = convertToEntity(userDAO);
        UserEntity savedEntity = userRepository.save(userEntity);

        return convertToDAO(savedEntity);
    }

    public UserDAO update(UserDAO userDAO) {
        UserEntity userEntity = convertToEntity(userDAO);
        UserEntity updatedEntity = userRepository.save(userEntity);
        return convertToDAO(updatedEntity);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    private UserDAO convertToDAO(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        UserDAO userDAO = new UserDAO();
        userDAO.setId(userEntity.getId());
        userDAO.setName(userEntity.getName());
        userDAO.setLogin(userEntity.getLogin());
        userDAO.setPassword(userEntity.getPassword());
        return userDAO;
    }

    private UserEntity convertToEntity(UserDAO userDAO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDAO.getId());
        userEntity.setName(userDAO.getName());
        userEntity.setLogin(userDAO.getLogin());
        userEntity.setPassword(userDAO.getPassword());
        return userEntity;
    }
}