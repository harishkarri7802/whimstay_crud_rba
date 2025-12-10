package com.whimstay.service;

import com.whimstay.dto.RegisterRequest;
import com.whimstay.dto.UserInternalDto;
import com.whimstay.entity.User;
import com.whimstay.exception.UserException;
import com.whimstay.exception.UserNotFound;
import com.whimstay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserInternalDto getUserByEmail(String email) {
        User userInternalDto=userRepository.findByEmail(email).orElseThrow(()-> new UserNotFound("user doest not found "));
        return UserInternalDto.builder().id(userInternalDto.getId())
                .role(userInternalDto.getRole())
                .email(userInternalDto.getEmail())
                .name(userInternalDto.getName())
                .password(userInternalDto.getPassword())
                .phoneNumber(userInternalDto.getPhoneNumber())
                .createdAt(userInternalDto.getCreatedAt())
                .updateAt(userInternalDto.getUpdateAt()).build();
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .role(registerRequest.getRole())
                .phoneNumber(registerRequest.getPhoneNumber())
                .email(registerRequest.getEmail())
                .createdAt(registerRequest.getCreatedAt())
                .updateAt(registerRequest.getUpdateAt())
                .password(passwordEncoder.encode(registerRequest.getPassword())).build();
        try{
           userRepository.save(user);
        }catch (Exception exception){
            throw new UserException("user cannot be created due to unexpected event");
        }
        return true;
    }
}
