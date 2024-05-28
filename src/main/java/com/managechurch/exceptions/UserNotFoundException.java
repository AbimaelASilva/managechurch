package com.managechurch.exceptions;

 public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() { super("Algo não deu certo!"); }

    public UserNotFoundException(String message) { super(message); }
 }