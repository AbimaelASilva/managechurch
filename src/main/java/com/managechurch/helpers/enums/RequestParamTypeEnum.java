package com.managechurch.helpers.enums;

public enum RequestParamTypeEnum {
    LOGIN,
    NAME;

    public String getLabel() {
        switch (this) {
            case LOGIN:
                return "Login";
            case NAME:
                return "Nome";

            default:
                throw new IllegalArgumentException("Unknown enum type");
        }
    }

    public static RequestParamTypeEnum enumFromString(String id) {
        switch (id) {
            case "login":
                return RequestParamTypeEnum.LOGIN;
            case "name":
            default:
                return RequestParamTypeEnum.NAME;

        }
    }
}