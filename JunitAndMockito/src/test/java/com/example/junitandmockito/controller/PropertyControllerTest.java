package com.example.junitandmockito.controller;

import com.example.junitandmockito.dto.PropertyDTO;
import com.example.junitandmockito.sercive.PropertyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {

    @InjectMocks
    private PropertyController propertyController;

    @Mock
    private PropertyService propertyService;

    @Test
    void testSaveProperty() {
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dto property");
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(1L);

        Mockito.when(propertyService.saveProperty(dto)).thenReturn(propertyDTO);

        ResponseEntity<PropertyDTO> responseEntity = propertyController.saveProperty(dto);
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    void testGetProperties() {
        List<PropertyDTO> propertyDTOS = new ArrayList<>();

        Mockito.when((propertyService.getAllProperties())).thenReturn(propertyDTOS);

        ResponseEntity<List<PropertyDTO>> responseEntity = propertyController.getAllProperties();
        Assertions.assertEquals(0, responseEntity.getBody().size());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    void testUpdatePropertyPrice() {
        PropertyDTO dto = new PropertyDTO();
        dto.setPrice(100.);
        Mockito.when(propertyService.updatePropertyPrice(dto, 1L)).thenReturn(dto);
        ResponseEntity<PropertyDTO> responseEntity = propertyController.updatePropertyPrice(dto, 1L);
        Assertions.assertEquals(100., responseEntity.getBody().getPrice());
        Assertions.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }
}
