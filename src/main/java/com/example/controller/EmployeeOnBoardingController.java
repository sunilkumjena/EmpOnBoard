package com.example.controller;

import com.example.exception.EmployeeNotFoundException;
import com.example.exception.GlobalException;
import com.example.repo.entity.Employee;
import com.example.service.EmployeeMgmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/employee/v1")
public class EmployeeOnBoardingController {

    @Autowired
    private EmployeeMgmtService employeeMgmtService;

    @PostMapping("/add")
    public  ResponseEntity<Employee> onBoardEmployee(@RequestBody Employee employee){
        if(employee == null){
            throw new GlobalException("Employee data is empty. Please add employee info and try again!");
        }
        return new ResponseEntity<Employee>(employeeMgmtService.onBoardEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getStudent(@PathVariable("id") Long employeeId){
        if(employeeId == null){
            throw new GlobalException("Employee id is null . Please add a valid employee id and try again!");
        }
        Optional<Employee> existedEmployee =  employeeMgmtService.getEmployee(employeeId);
        if(!existedEmployee.isPresent()){
            throw new EmployeeNotFoundException("There no valid employee found on the given Id. Please check the id and try again!");
        }
        return new ResponseEntity<Employee>(employeeMgmtService.getEmployee(employeeId).get(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        if (employee == null || employee.getId() == null) {
            throw new GlobalException("Employee data or ID is missing. Please provide valid employee info and try again!");
        }

        Optional<Employee> existingEmployee = employeeMgmtService.getEmployee(employee.getId());
        if (!existingEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with the given ID does not exist. Please check the ID and try again!");
        }

        Employee updatedEmployee = employeeMgmtService.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/suspend/{id}")
    public ResponseEntity<Employee> suspendEmployee(@PathVariable("id") Long id) {
        if (id == null) {
            throw new GlobalException("Employee ID is null. Please provide a valid employee ID and try again!");
        }

        Optional<Employee> existingEmployee = employeeMgmtService.getEmployee(id);
        if (!existingEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with the given ID does not exist. Please check the ID and try again!");
        }

        Employee suspendedEmployee = employeeMgmtService.suspendEmployee(id);
        return new ResponseEntity<>(suspendedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Employee> removeEmployee(@PathVariable("id") Long id) {
        if (id == null) {
            throw new GlobalException("Employee ID is null. Please provide a valid employee ID and try again!");
        }

        Optional<Employee> existingEmployee = employeeMgmtService.getEmployee(id);
        if (!existingEmployee.isPresent()) {
            throw new EmployeeNotFoundException("Employee with the given ID does not exist. Please check the ID and try again!");
        }

        Employee removedEmployee = employeeMgmtService.removeEmployee(id);
        return new ResponseEntity<>(removedEmployee, HttpStatus.OK);
    }

}
