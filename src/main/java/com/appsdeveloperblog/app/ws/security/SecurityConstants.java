package com.appsdeveloperblog.app.ws.security;

import static com.appsdeveloperblog.app.ws.SpringApplicationContext.getBean;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 84000000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users";

    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) getBean("appProperties");
        return appProperties.getTokenSecret();
    }

}
