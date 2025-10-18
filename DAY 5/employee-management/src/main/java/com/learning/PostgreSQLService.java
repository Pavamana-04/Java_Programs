package com.learning;

import com.learning.PostgreSQLConnection;
import com.learning.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLService {

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees (name, email, age) VALUES (?, ?, ?)";

        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setInt(3, employee.getAge());
            pstmt.executeUpdate();

            System.out.println("Employee added: " + employee.getName());
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = PostgreSQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setEmail(rs.getString("email"));
                employee.setAge(rs.getInt("age"));
                employees.add(employee);
            }
        }
        return employees;
    }

    public void updateEmployeeAge(String name, int newAge) throws SQLException {
        String sql = "UPDATE employees SET age = ? WHERE name = ?";

        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newAge);
            pstmt.setString(2, name);
            pstmt.executeUpdate();

            System.out.println("Updated age for: " + name);
        }
    }

    public void deleteEmployee(String name) throws SQLException {
        String sql = "DELETE FROM employees WHERE name = ?";

        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();

            System.out.println("Deleted employee: " + name);
        }
    }
}