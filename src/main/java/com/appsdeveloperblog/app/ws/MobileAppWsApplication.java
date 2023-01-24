package com.appsdeveloperblog.app.ws;

import com.appsdeveloperblog.app.ws.ui.controller.UserController;
import com.appsdeveloperblog.app.ws.ui.model.request.UserDetailsRequestModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MobileAppWsApplication {

    public static void main(String[] args) {
        /*ConfigurableApplicationContext run = */
        SpringApplication.run(MobileAppWsApplication.class, args);
//        UserController bean = run.getBean(UserController.class);
//        bean.createUsers(null);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public  SpringApplicationContext springApplicationContext (){
        return new SpringApplicationContext();
    }


}
