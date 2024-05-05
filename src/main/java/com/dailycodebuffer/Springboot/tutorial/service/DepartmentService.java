package com.dailycodebuffer.Springboot.tutorial.service;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.error.DepartmentNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    ResponseEntity<String> deleteDepartmentById(Long departmentId);

    Department updateDepartment(Long departmentId, Department department);

    Department fetchDepartmentByName(String departmentName);

}
