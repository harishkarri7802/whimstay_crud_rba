package com.whimstay.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class LocationDto {

    @NotBlank(message = "address is required")
    @Size(min = 10, max = 100, message = "address must be between 10 and 100 characters")
    private String address;

    @Size(min = 5, max = 50, message = "city must be between 5 and 50 characters")
    @NotBlank(message = "city is required")
    private String city;

    @Size(min = 5, max = 50, message = "state must be between 5 and 50 characters")
    @NotBlank(message = "state is required")
    private String state;

    @Size(min = 5, max = 50, message = "country must be between 5 and 50 characters")
    @NotBlank(message = "country is required")
    private String country;
}
