package com.managechurch.entities;

import com.managechurch.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.DynamicInsert;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@DynamicInsert
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",  length = 60)
    private String name;

    @Column(name = "login",  length = 30, unique = true)
    private String login;

    @Column(name = "password",  length = 30)
    private String password;

    public UserEntity(Integer id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public UserEntity(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.login = userDTO.getLogin();
        this.password = userDTO.getPassword();
    }

    public UserEntity() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserDTO> convertToDtoList(List<UserEntity> userEntityList) {

        List<UserDTO> userDtos = userEntityList.stream()
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getLogin(),
                        userEntity.getPassword()))
                .collect(Collectors.toList());

        return userDtos;

    }

}
