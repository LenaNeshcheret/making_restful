package com.appsdeveloperblog.app.ws.shared.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionId = 1L ;
    private long Id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private  String emailVerificationToken;
    private  Boolean emailVerificationStatus = false;


}
