package com.whimstay.dto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PropertyDto {
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 20, max = 2000, message = "Description must be between 20 and 2000 characters")
    private String description;

    @NotNull(message = "Bedrooms count is required")
    @Min(value = 0, message = "Bedrooms cannot be negative")
    @Max(value = 5, message = "Maximum 50 bedrooms allowed")
    private Integer bedrooms;

    @NotNull(message = "Bathrooms count is required")
    @Min(value = 1, message = "At least 1 bathroom is required")
    @Max(value = 5, message = "Maximum 50 bathrooms allowed")
    private Integer bathroms;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be greater than 0")
    @Digits(integer = 6, fraction = 2, message = "Price format invalid (e.g., 999999.99)")
    private Double  pricePerNight;

    @PositiveOrZero(message = "Original price must be zero or positive")
    @Digits(integer = 6, fraction = 2, message = "Original price format invalid")
    private Double originalPrice;

    @NotEmpty(message = "At least one available date is required")
    private Set<LocalDate> availableDate;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedBy
    private LocalDate updateAt;

    @NotNull(message = "Location is required")
    @Valid
    private LocationDto location;

    @NotEmpty(message = "At least one amenity must be provided")
    @Size(max = 20, message = "Maximum 20 amenities allowed")
    private String[] amenity;
}
