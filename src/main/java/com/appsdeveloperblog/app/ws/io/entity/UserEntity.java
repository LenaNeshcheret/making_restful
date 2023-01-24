package com.appsdeveloperblog.app.ws.io.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity (name = "users")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column (nullable = false)
    private String userId;

    @Column (nullable = false, length = 50)
    private String firstName;

    @Column (nullable = false, length = 50)
    private String lastName;

    @Column (nullable = false, length = 120, unique = true)
    private String email;

    private String encryptedPassword;
    private  String emailVerificationToken;

    @Column(nullable = false)
    private  Boolean emailVerificationStatus = false;

}
