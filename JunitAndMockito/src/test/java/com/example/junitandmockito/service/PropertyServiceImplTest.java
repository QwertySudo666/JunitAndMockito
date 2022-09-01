package com.example.junitandmockito.service;

import com.example.junitandmockito.converter.PropertyConverter;
import com.example.junitandmockito.dto.PropertyDTO;
import com.example.junitandmockito.entity.PropertyEntity;
import com.example.junitandmockito.repository.PropertyRepository;
import com.example.junitandmockito.sercive.impl.PropertyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PropertyServiceImplTest {
    @InjectMocks
    private PropertyServiceImpl propertyService;

    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private PropertyConverter propertyConverter;

    @Test
    void testSaveProperty_Success() {
        PropertyDTO dto = preparePropertyDTOTest();

        PropertyDTO savedDto = new PropertyDTO();
        savedDto.setId(1L);

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setPrice(1.);

        PropertyEntity savedEntity = preparePropertyEntityTest();

        Mockito.when(propertyConverter.convertDTOtoEntity(Mockito.any())).thenReturn(propertyEntity);
        Mockito.when(propertyRepository.save(Mockito.any())).thenReturn(savedEntity);
        Mockito.when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDto);

        PropertyDTO result = propertyService.saveProperty(dto);
        Assertions.assertEquals(result.getId(), savedDto.getId());
    }

    @Test
    void testGetAllProperties_Success() {
        List<PropertyEntity> propertyEntities = new ArrayList<>();

        Mockito.when(propertyRepository.findAll()).thenReturn(propertyEntities);
        //Mockito.when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(dto);
        List<PropertyDTO> propertyDTOS = new ArrayList<>();

        propertyDTOS = propertyService.getAllProperties();

        Assertions.assertEquals(propertyEntities.size(), propertyDTOS.size());
    }

    @Test
    void testUpdateProperty_Success() {
        PropertyDTO savedDTO = new PropertyDTO();
        savedDTO.setId(1L);
        savedDTO.setTitle("1");

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setId(1L);
        propertyEntity.setTitle("1");

        Mockito.when(propertyRepository.findById(Mockito.any())).thenReturn(Optional.of(propertyEntity));
        Mockito.when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

        PropertyDTO updatedDto = propertyService.updateProperty(savedDTO, 1L);
        Assertions.assertEquals(savedDTO.getTitle(), updatedDto.getTitle());
    }

    @Test
    void testUpdateDesc_Success() {
        PropertyEntity savedPE = preparePropertyEntityTest();
        savedPE.setDescription("updated");
        PropertyDTO dtoResponse = preparePropertyDTOTest();
        dtoResponse.setDescription("updated");

        Mockito.when(propertyRepository.findById(Mockito.any())).thenReturn(Optional.of(savedPE));
        Mockito.when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(dtoResponse);

        PropertyDTO updatedDTO = propertyService.updatePropertyDescription(dtoResponse, 1L);
        Assertions.assertEquals(dtoResponse.getDescription(), updatedDTO.getDescription());
    }

    private PropertyDTO preparePropertyDTOTest() {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("1");
        dto.setAddress("1");
        dto.setOwnerEmail("1");
        dto.setOwnerName("1");
        dto.setPrice(1.);
        dto.setDescription("1");
        return dto;
    }

    private PropertyEntity preparePropertyEntityTest() {
        PropertyEntity entity = new PropertyEntity();
        entity.setId(1L);
        entity.setTitle("1");
        entity.setAddress("1");
        entity.setOwnerEmail("1");
        entity.setOwnerName("1");
        entity.setPrice(1.);
        entity.setDescription("1");
        return entity;
    }
}
