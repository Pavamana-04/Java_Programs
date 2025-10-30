package com.example.Employee_Usecase;

import com.example.Employee_Usecase.model.Employee;
import com.example.Employee_Usecase.repository.EmployeeRepository;
import com.example.Employee_Usecase.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee1;
    private Employee employee2;

    @BeforeEach
    void setUp() {
        employee1 = new Employee("Pavan", "Software Engineer");
        employee1.setId(1L);

        employee2 = new Employee("Bharath", "Product Manager");
        employee2.setId(2L);
    }

    @Test
    void testGetAllEmployees() {

        List<Employee> employees = Arrays.asList(employee1, employee2);
        when(employeeRepository.findAll()).thenReturn(employees);


        List<Employee> result = employeeService.getAllEmployees();


        assertNotNull(result);
        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById_Found() {

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));


        Optional<Employee> result = employeeService.getEmployeeById(1L);


        assertTrue(result.isPresent());
        assertEquals("Pavan", result.get().getEmployeeName());
        verify(employeeRepository, times(1)).findById(1L);
    }

    @Test
    void testGetEmployeeById_NotFound() {

        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());


        Optional<Employee> result = employeeService.getEmployeeById(99L);


        assertFalse(result.isPresent());
        verify(employeeRepository, times(1)).findById(99L);
    }

    @Test
    void testCreateEmployee() {

        Employee newEmployee = new Employee("Bharath", "QA Engineer");
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);


        Employee result = employeeService.createEmployee(newEmployee);


        assertNotNull(result);
        assertEquals("Bharath", result.getEmployeeName());
        assertEquals("QA Engineer", result.getEmployeePosition());
        verify(employeeRepository, times(1)).save(newEmployee);
    }

    @Test
    void testUpdateEmployee_Success() {

        Employee updatedDetails = new Employee("Pavan Updated", "Senior Software Engineer");
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee1));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedDetails);


        Employee result = employeeService.updateEmployee(1L, updatedDetails);


        assertNotNull(result);
        assertEquals("Pavan Updated", result.getEmployeeName());
        assertEquals("Senior Software Engineer", result.getEmployeePosition());
        verify(employeeRepository, times(1)).findById(1L);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void testUpdateEmployee_NotFound() {

        Employee updatedDetails = new Employee("Pavan Updated", "Senior Software Engineer");
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());


        Employee result = employeeService.updateEmployee(99L, updatedDetails);


        assertNull(result);
        verify(employeeRepository, times(1)).findById(99L);
        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void testDeleteEmployee_Success() {

        when(employeeRepository.existsById(1L)).thenReturn(true);


        boolean result = employeeService.deleteEmployee(1L);


        assertTrue(result);
        verify(employeeRepository, times(1)).existsById(1L);
        verify(employeeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEmployee_NotFound() {

        when(employeeRepository.existsById(99L)).thenReturn(false);


        boolean result = employeeService.deleteEmployee(99L);


        assertFalse(result);
        verify(employeeRepository, times(1)).existsById(99L);
        verify(employeeRepository, never()).deleteById(99L);
    }
}
