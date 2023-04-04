package com.vonco.jwt.model;
import lombok.Data;

@Data
public class UserExt{

    private String id;

    private String username;

    private String password;

    private String menuCodes;
}
