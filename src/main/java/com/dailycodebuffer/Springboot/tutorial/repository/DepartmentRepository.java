package com.dailycodebuffer.Springboot.tutorial.repository;

import com.dailycodebuffer.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // nativeQuery = true -> SQL standard, if I dont put this flag it will only recognize JPQL
    // ?1 para primeiro parametro, ?2 para segundo parametro, assim por diante
    @Query(value =
            "SELECT * FROM DEPARTMENT " +
            "WHERE DEPARTMENT_NAME = ?1",
            nativeQuery = true)
    Department findByDepartmentName(String departmentName);

    Department findByDepartmentNameIgnoreCase(String departmentName);

}
