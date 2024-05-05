package com.dailycodebuffer.Springboot.tutorial.service;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department =
                Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentCode("IT-06")
                .departmentAddress("Ahmedabad")
                .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase(anyString())).thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on Valid Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName = "IT";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertEquals(departmentName, found.getDepartmentName());
    }

    @Test
    @DisplayName("Get Data based on Invalid Department Name")
    public void whenInvalidDepartmentName_thenDepartmentShouldNotFound(){
        String departmentName = "Tech";
        Department found = departmentService.fetchDepartmentByName(departmentName);

        assertNotEquals(departmentName, found.getDepartmentName());
    }

}