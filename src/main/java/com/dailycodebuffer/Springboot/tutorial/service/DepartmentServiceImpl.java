package com.dailycodebuffer.Springboot.tutorial.service;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import com.dailycodebuffer.Springboot.tutorial.error.DepartmentNotFoundException;
import com.dailycodebuffer.Springboot.tutorial.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department Not Available"));
    }

    @Override
    public ResponseEntity<String> deleteDepartmentById(Long departmentId) {
        if(departmentRepository.findById(departmentId).isPresent()){
            departmentRepository.deleteById(departmentId);
            return ResponseEntity.status(HttpStatus.OK).body("Department deleted successfully!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found!");
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department with id " + departmentId + " doesn't exist in our database."));

        if(Objects.nonNull(department.getDepartmentName()) && !department.getDepartmentName().isEmpty()){
            depDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentCode()) && !department.getDepartmentCode().isEmpty()){
            depDB.setDepartmentCode(department.getDepartmentCode());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) && !department.getDepartmentAddress().isEmpty()){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }

        return departmentRepository.save(depDB);
    }

    @Override
    public Department fetchDepartmentByName(String departmentName) {
        return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
    }

}
