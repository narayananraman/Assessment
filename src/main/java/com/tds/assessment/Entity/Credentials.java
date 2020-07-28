package com.tds.assessment.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("creds")
public class Credentials {
    @Value("${user.id}")
    private String username;

    @Value("${user.password}")
    private String password;

    @Value("${user.role}")
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {

        System.out.println("Using the setter to set "+login);
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}

