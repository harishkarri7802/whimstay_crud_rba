package com.whimstay.auth;

import com.whimstay.dto.ApiResponse;
import com.whimstay.dto.AuthRequest;
import com.whimstay.dto.AuthResponse;
import com.whimstay.dto.RegisterRequest;
import com.whimstay.jwt.CustomUserDetails;
import com.whimstay.jwt.JwtHelper;
import com.whimstay.service.UserService;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthApi {
    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<@NonNull ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = null;
            if (userDetails != null) {
                token = jwtHelper.generateToken(userDetails);
            }

            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Login successful", new AuthResponse(token))
            );

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, "Invalid username or password", null));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }


    @PostMapping("/register")
    public ResponseEntity<@NonNull Object> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            boolean isRegistered = userService.register(registerRequest);
            if (isRegistered) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body("User registered successfully");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Registration failed");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }
}


