package com.managechurch.dto;

import com.managechurch.entities.UserEntity;

public class UserDTO {

    //Criei o body da requisição POST para insominica com as propiedades e valores ficticios:

    private Integer id;

    private String name;

    private String login;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    //https://www.youtube.com/watch?v=ninjPTQjNSI
    //No minuto 7:25 tem exemplo de como converter uma entidade para um DTO. O Spring Boot faz isso automaticamente
    public UserDTO(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.login = userEntity.getLogin();
        this.password = userEntity.getPassword();
     
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

}
