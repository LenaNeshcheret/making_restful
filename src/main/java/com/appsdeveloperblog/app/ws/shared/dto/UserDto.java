package com.appsdeveloperblog.app.ws.shared.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter@Setter
public class UserDto implements Serializable {

    private static final long serialVersionId = 1L ;
    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private  String emailVerificationToken;
    private  Boolean emailVerificationStatus = false;
    private List<AddressDTO> addresses;


}
