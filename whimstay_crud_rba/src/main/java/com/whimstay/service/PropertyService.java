package com.whimstay.service;

import com.whimstay.dto.PropertyDto;

import java.util.List;


public interface PropertyService {
    PropertyDto createProperty(PropertyDto propertyDto);
    List<PropertyDto> getAllMyProperty();
    void deleteMyProperty(String id);
    PropertyDto updateProperty(String id,PropertyDto propertyDto);
    PropertyDto findByPropertyId(String id);
}
