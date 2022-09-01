package com.example.junitandmockito.controller;

import com.example.junitandmockito.dto.CalculatorDTO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@ExtendWith(MockitoExtension.class)

public class CalculatorControllerTest {
    @InjectMocks
    private CalculatorController calculatorController;

    Double num1;
    Double num2;

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @BeforeEach
    void init() {
        num1 = 2.;
        num2 = 3.;
        System.out.println("before each");
    }

    @AfterEach
    void destroy() {
        num1 = null;
        num2 = null;
        System.out.println("after each");
    }

    @Test
    public void addTest_Success() {
        Double result = calculatorController.add(num1, num2);
        Assertions.assertEquals(5, result);
    }

    @Test
    public void addTest_Failure() {
        Double result = calculatorController.add(1., 1.);
        Assertions.assertNotEquals(7, result);
    }

    @Test
    public void testSubFunction_num1_gt_num2() {
        Double result = calculatorController.subtract(num1, num2);
        Assertions.assertEquals(1., result);
    }

    @Test
    void testMultiply() {
        CalculatorDTO calculatorDTO = new CalculatorDTO();
        calculatorDTO.setNum1(1.);
        calculatorDTO.setNum2(1.);
        calculatorDTO.setNum3(1.);
        calculatorDTO.setNum4(1.);

        ResponseEntity<Double> responseEntity = calculatorController.multiply(calculatorDTO);
        Assertions.assertEquals(1, responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue(), "Expecting the status as Created");
    }
}
