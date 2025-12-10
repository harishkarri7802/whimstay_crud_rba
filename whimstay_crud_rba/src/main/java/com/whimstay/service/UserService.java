package com.whimstay.service;

import com.whimstay.dto.RegisterRequest;
import com.whimstay.dto.UserInternalDto;

public interface UserService {
    UserInternalDto getUserByEmail(String email);
    boolean register(RegisterRequest registerRequest);
}
