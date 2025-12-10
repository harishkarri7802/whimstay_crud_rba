package com.whimstay.service;

import com.whimstay.dto.PropertyDto;
import com.whimstay.entity.Property;
import com.whimstay.exception.PropertyException;
import com.whimstay.repository.PropertyRepository;
import jakarta.el.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    private  final PropertyRepository propertyRepository;
    @Autowired

    PropertyServiceImpl(PropertyRepository propertyRepository){
        this.propertyRepository=propertyRepository;
    }

    @Override
    public PropertyDto createProperty(PropertyDto propertyDto) {
        Property property = Property.builder().
                title(propertyDto.getTitle())
                .amenity(propertyDto.getAmenity())
                .bathroms(propertyDto.getBathroms())
                .createdAt(propertyDto.getCreatedAt())
                .availableDate(propertyDto.getAvailableDate())
                .description(propertyDto.getDescription())
                .location(propertyDto.getLocation())
                .originalPrice(propertyDto.getOriginalPrice())
                .pricePerNight(propertyDto.getPricePerNight())
                .bedrooms(propertyDto.getBedrooms())
                .updateAt(propertyDto.getUpdateAt())
                .build();
        Property propertyResponse =null;
        try{
            propertyResponse =propertyRepository.save(property);
        }catch (Exception e){
            throw new PropertyException("property cannot be saved due to unexpected error");
        }
        return propertyResponse.toDto();
    }

    @Override
    public List<PropertyDto> getAllMyProperty() {
        return propertyRepository.findAll().stream().map(this::convertIntoDto).toList();
    }

    @Override
    public void deleteMyProperty(String id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public PropertyDto updateProperty(String id, PropertyDto propertyDto) {
        Property existingProperty = propertyRepository.findById(id).orElseThrow(()->new PropertyNotFoundException("property does not exits.."));
        existingProperty.setId(id);
        existingProperty.setBedrooms(propertyDto.getBedrooms());
        existingProperty.setDescription(propertyDto.getDescription());
        existingProperty.setAmenity(propertyDto.getAmenity());
        existingProperty.setLocation(propertyDto.getLocation());
        existingProperty.setTitle(propertyDto.getTitle());
        existingProperty.setAvailableDate(propertyDto.getAvailableDate());
        existingProperty.setOriginalPrice(propertyDto.getOriginalPrice());
        existingProperty.setUpdateAt(propertyDto.getUpdateAt());
        existingProperty.setPricePerNight(propertyDto.getPricePerNight());
        existingProperty.setUpdateAt(propertyDto.getUpdateAt());
        existingProperty.setCreatedAt(propertyDto.getCreatedAt());
        existingProperty.setBathroms(propertyDto.getBathroms());
        try{
            propertyRepository.save(existingProperty);
        } catch (Exception e) {
            throw new PropertyException("error occurred while adding property");
        }
        return existingProperty.toDto();
    }

    @Override
    public PropertyDto findByPropertyId(String id) {
        return propertyRepository.findById(id).orElseThrow(()-> new PropertyNotFoundException("property not found ")).toDto();
    }

    private PropertyDto convertIntoDto(Property property){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setAmenity(property.getAmenity());
        propertyDto.setBathroms(property.getBathroms());
        propertyDto.setDescription(property.getDescription());
        propertyDto.setLocation(property.getLocation());
        propertyDto.setBedrooms(property.getBedrooms());
        propertyDto.setCreatedAt(property.getCreatedAt());
        propertyDto.setTitle(property.getTitle());
        propertyDto.setAvailableDate(property.getAvailableDate());
        propertyDto.setOriginalPrice(property.getOriginalPrice());
        propertyDto.setUpdateAt(property.getUpdateAt());
        propertyDto.setPricePerNight(property.getPricePerNight());
        return propertyDto;
    }
}
