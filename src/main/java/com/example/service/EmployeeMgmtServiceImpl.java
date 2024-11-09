package com.example.service;

import com.example.repo.EmployeeMgmtRepo;
import com.example.repo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeMgmtServiceImpl implements EmployeeMgmtService {

    @Autowired
    private EmployeeMgmtRepo employeeMgmtRepo;

    @Override
    public Employee onBoardEmployee(Employee employee) {
        return employeeMgmtRepo.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employeeMgmtRepo.existsById(employee.getId())) {
            return employeeMgmtRepo.save(employee);
        }
        return null;
    }

    @Override
    public Optional<Employee> getEmployee(Long id) {
        return employeeMgmtRepo.findById((long) id);
    }

    @Override
    public Optional<Employee> getEmployee(long id) {
        return employeeMgmtRepo.findById((long) id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeMgmtRepo.findAll();
    }

    @Override
    public Employee suspendEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeMgmtRepo.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setSuspended(true);
            return employeeMgmtRepo.save(employee);
        }
        return null;
    }

    @Override
    public Employee removeEmployee(Long id) {
        Optional<Employee> employeeOptional = employeeMgmtRepo.findById((long) id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employeeMgmtRepo.delete(employee);
            return employee;
        }
        return null;
    }
}
