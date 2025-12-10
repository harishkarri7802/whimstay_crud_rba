package com.whimstay.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
