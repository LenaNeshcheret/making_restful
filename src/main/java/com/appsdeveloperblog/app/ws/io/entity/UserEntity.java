package com.appsdeveloperblog.app.ws.io.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity (name = "users")
@Getter@Setter
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

    @OneToMany(mappedBy = "userDetails", cascade = {CascadeType.ALL})
    private List<AddressEntity> addresses;

}
