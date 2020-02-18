package com.springboot.server.config;

import com.springboot.server.services.UserService;
import com.springboot.server.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean("userService")
    public UserService getUserService(){
        return new UserServiceImpl();
    }

    @Bean("encoder")
    public PasswordEncoder getPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
