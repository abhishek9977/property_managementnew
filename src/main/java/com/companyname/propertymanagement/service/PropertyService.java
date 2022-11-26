package com.companyname.propertymanagement.service;

import com.companyname.propertymanagement.dto.PropertyDTO;

import java.util.List;

public interface PropertyService
{
    PropertyDTO saveProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();
    List<PropertyDTO> getPropertiesById(Long userId);
    PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyId);

    PropertyDTO updateDescription(PropertyDTO propertyDTO,Long propertyId);

    void delete(Long propertyId);
}
