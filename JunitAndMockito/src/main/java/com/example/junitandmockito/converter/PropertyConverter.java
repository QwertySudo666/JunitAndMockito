package com.example.junitandmockito.converter;

import com.example.junitandmockito.dto.PropertyDTO;
import com.example.junitandmockito.entity.PropertyEntity;
import org.springframework.stereotype.Component;

@Component
public class PropertyConverter {
    public PropertyEntity convertDTOtoEntity(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setAddress(propertyDTO.getAddress() + "1");
        propertyEntity.setOwnerEmail(propertyDTO.getOwnerEmail());
        propertyEntity.setOwnerName(propertyDTO.getOwnerName());
        propertyEntity.setPrice(propertyDTO.getPrice());
        propertyEntity.setDescription(propertyDTO.getDescription());
        return propertyEntity;
    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setAddress(propertyEntity.getAddress() + "1");
        propertyDTO.setOwnerEmail(propertyEntity.getOwnerEmail());
        propertyDTO.setOwnerName(propertyEntity.getOwnerName());
        propertyDTO.setPrice(propertyEntity.getPrice());
        propertyDTO.setDescription(propertyEntity.getDescription());
        return propertyDTO;
    }
}
