package com.example.junitandmockito.converter;

import com.example.junitandmockito.dto.PropertyDTO;
import com.example.junitandmockito.entity.PropertyEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PropertyConverterTest {
    @InjectMocks
    private PropertyConverter propertyConverter;

    @Test
    void convertDTOtoEntity_Success() {
        PropertyDTO dto = preparePropertyDTOTest();
        PropertyEntity propertyEntity = propertyConverter.convertDTOtoEntity(dto);
        Assertions.assertEquals(dto.getTitle(), propertyEntity.getTitle());
    }

    @Test
    void convertEntityToDTO_Success() {
        PropertyEntity entity = preparePropertyEntityTest();
        PropertyDTO dto = propertyConverter.convertEntityToDTO(entity);
        Assertions.assertEquals(entity.getTitle(), dto.getTitle());
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
