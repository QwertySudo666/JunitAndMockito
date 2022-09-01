package com.example.junitandmockito.sercive.impl;

import com.example.junitandmockito.converter.PropertyConverter;
import com.example.junitandmockito.dto.PropertyDTO;
import com.example.junitandmockito.entity.PropertyEntity;
import com.example.junitandmockito.repository.PropertyRepository;
import com.example.junitandmockito.sercive.PropertyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> propertyEntities = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propertyDTOS = new ArrayList<>();
        for (PropertyEntity pe : propertyEntities) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(pe);
            propertyDTOS.add(dto);
        }
        return propertyDTOS;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dtoResponse = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntityFromDB = optionalPropertyEntity.get();
            propertyEntityFromDB.setTitle(propertyDTO.getTitle());
            propertyEntityFromDB.setAddress(propertyDTO.getAddress());
            propertyEntityFromDB.setOwnerEmail(propertyDTO.getOwnerEmail());
            propertyEntityFromDB.setOwnerName(propertyDTO.getOwnerName());
            propertyEntityFromDB.setPrice(propertyDTO.getPrice());
            propertyEntityFromDB.setDescription(propertyDTO.getDescription());
            dtoResponse = propertyConverter.convertEntityToDTO(propertyEntityFromDB);
            propertyRepository.save(propertyEntityFromDB);
        }
        return dtoResponse;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dtoResponse = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntityFromDB = optionalPropertyEntity.get();
            propertyEntityFromDB.setDescription(propertyDTO.getDescription());
            dtoResponse = propertyConverter.convertEntityToDTO(propertyEntityFromDB);
            propertyRepository.save(propertyEntityFromDB);
        }
        return dtoResponse;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optionalPropertyEntity = propertyRepository.findById(propertyId);
        PropertyDTO dtoResponse = null;
        if (optionalPropertyEntity.isPresent()) {
            PropertyEntity propertyEntityFromDB = optionalPropertyEntity.get();
            propertyEntityFromDB.setPrice(propertyDTO.getPrice());
            dtoResponse = propertyConverter.convertEntityToDTO(propertyEntityFromDB);
            propertyRepository.save(propertyEntityFromDB);
        }
        return dtoResponse;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
