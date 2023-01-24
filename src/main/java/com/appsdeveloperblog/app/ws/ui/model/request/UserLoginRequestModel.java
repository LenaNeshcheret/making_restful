package com.appsdeveloperblog.app.ws.ui.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequestModel {
    private String email;
    private String password;

}
