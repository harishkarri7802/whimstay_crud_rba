package com.whimstay.jwt;

import com.whimstay.dto.UserInternalDto;
import com.whimstay.service.UserServiceImp;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServiceImp userServiceImp;
    @Autowired
    public CustomUserDetailsService(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String email)
            throws UsernameNotFoundException {
        UserInternalDto user = userServiceImp.getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + email);
        }
        return CustomUserDetails.builder()
                .id(user.getId())
                .role(user.getRole())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
