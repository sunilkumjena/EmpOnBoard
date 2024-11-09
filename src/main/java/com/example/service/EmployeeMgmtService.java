package com.example.service;

import com.example.repo.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeMgmtService {

    Employee onBoardEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Optional<Employee> getEmployee(Long id);

    Optional<Employee> getEmployee(long id);

    List<Employee> getAllEmployee();
    Employee suspendEmployee(Long id);
    Employee removeEmployee(Long id);
}
