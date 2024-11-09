package com.example.repo;

import com.example.repo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMgmtRepo extends JpaRepository<Employee, Long> {
}
