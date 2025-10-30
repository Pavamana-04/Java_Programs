package com.example.Employee_Usecase;

import com.example.Employee_Usecase.Controller.EmployeeController;
import com.example.Employee_Usecase.model.Employee;
import com.example.Employee_Usecase.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee1;
    private Employee employee2;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        objectMapper = new ObjectMapper();

        employee1 = new Employee("Pavan", "Software Engineer");
        employee1.setId(1L);

        employee2 = new Employee("Ram", "Product Manager");
        employee2.setId(2L);
    }

    @Test
    void testGetAllEmployees() throws Exception {

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(employee1, employee2));


        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].employeeName").value("Pavan"))
                .andExpect(jsonPath("$[1].employeeName").value("Ram"));
    }

    @Test
    void testGetEmployeeById_Found() throws Exception {

        when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(employee1));


        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Pavan"))
                .andExpect(jsonPath("$.employeePosition").value("Software Engineer"));
    }

    @Test
    void testGetEmployeeById_NotFound() throws Exception {

        when(employeeService.getEmployeeById(99L)).thenReturn(Optional.empty());


        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreateEmployee() throws Exception {

        Employee newEmployee = new Employee("Pavan", "QA Engineer");
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(newEmployee);


        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEmployee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.employeeName").value("Pavan"));
    }

    @Test
    void testUpdateEmployee_Success() throws Exception {

        Employee updatedEmployee = new Employee("Ram Updated", "Senior Software Engineer");
        when(employeeService.updateEmployee(anyLong(), any(Employee.class))).thenReturn(updatedEmployee);


        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Ram Updated"));
    }

    @Test
    void testUpdateEmployee_NotFound() throws Exception {

        when(employeeService.updateEmployee(anyLong(), any(Employee.class))).thenReturn(null);


        mockMvc.perform(put("/api/employees/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee_Success() throws Exception {

        when(employeeService.deleteEmployee(1L)).thenReturn(true);


        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteEmployee_NotFound() throws Exception {

        when(employeeService.deleteEmployee(99L)).thenReturn(false);


        mockMvc.perform(delete("/api/employees/99"))
                .andExpect(status().isNotFound());
    }
}