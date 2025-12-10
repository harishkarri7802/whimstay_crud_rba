package com.whimstay.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInternalDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
