package com.whimstay.entity;

import com.whimstay.dto.LocationDto;
import com.whimstay.dto.PropertyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "properties")
public class Property {
    @Id
    private String id;
    private String title;
    private String description;
    private Integer bedrooms;
    private Integer bathroms;
    @Field("price_per_night")
    private Double  pricePerNight;
    @Field("original_price")
    private Double originalPrice;
    @Field("available_date")
    private Set<LocalDate> availableDate;

    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updateAt;
    private LocationDto location;
    private String[] amenity;
    public PropertyDto toDto(){
        return new PropertyDto(this.title,this.description,this.bedrooms,this.bathroms,this.pricePerNight,this.originalPrice,this.availableDate,this.createdAt,this.updateAt,this.location,this.amenity);
    }


}
