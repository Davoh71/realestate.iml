package com.example.realestate.service;

import com.example.realestate.model.CarentUser;
import com.example.realestate.model.User;
import com.example.realestate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository userrepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> byEmail = userrepository.findByEmail(s);
        if (!byEmail.isPresent()){
            throw  new UsernameNotFoundException("User with"+ s +"das not exist");
        }
        return new CarentUser(byEmail.get());
    }
}
