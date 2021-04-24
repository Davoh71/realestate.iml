package com.example.realestate.model;


import org.springframework.security.core.authority.AuthorityUtils;


public class CarentUser extends  org.springframework.security.core.userdetails.User {

    private User user;

    public CarentUser(User user){
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user=user;
    }

    public User getUser() {
        return user;
    }
}
