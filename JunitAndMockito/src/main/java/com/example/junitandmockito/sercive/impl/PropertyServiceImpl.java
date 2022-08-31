package com.example.junitandmockito.sercive.impl;

import com.example.junitandmockito.converter.PropertyConverter;
import com.example.junitandmockito.dto.PropertyDTO;
import com.example.junitandmockito.entity.PropertyEntity;
import com.example.junitandmockito.repository.PropertyRepository;
import com.example.junitandmockito.sercive.PropertyService;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final PropertyConverter propertyConverter;

    public PropertyServiceImpl(PropertyRepository propertyRepository, PropertyConverter propertyConverter) {
        this.propertyRepository = propertyRepository;
        this.propertyConverter = propertyConverter;
    }

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {
        PropertyEntity propertyEntity = propertyRepository.save(propertyConverter.convertDTOtoEntity(propertyDTO));
        PropertyDTO dto = propertyConverter.convertEntityToDTO(propertyEntity);
        return dto;
    }
}
