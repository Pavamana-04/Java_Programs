package com.example.Employee_Usecase.service;

import com.example.Employee_Usecase.model.Employee;
import com.example.Employee_Usecase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }


    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }


    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setEmployeeName(employeeDetails.getEmployeeName());
            employee.setEmployeePosition(employeeDetails.getEmployeePosition());
            return employeeRepository.save(employee);
        }
        return null;
    }


    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}