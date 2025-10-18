package com.learning;

import com.learning.Employee;
import com.learning.EmployeeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EmployeeService employeeService = new EmployeeService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Pavan & Pava Employee System ===");
        System.out.println("PostgreSQL Database Connection");

        try {
            boolean running = true;
            while (running) {
                showMenu();
                System.out.print("Choose (1-5): ");
                String input = scanner.nextLine().trim();

                try {
                    int choice = Integer.parseInt(input);

                    switch (choice) {
                        case 1:
                            addEmployee();
                            break;
                        case 2:
                            showAllEmployees();
                            break;
                        case 3:
                            searchEmployee();
                            break;
                        case 4:
                            updateEmployeeAge();
                            break;
                        case 5:
                            deleteEmployee();
                            break;
                        case 6:
                            running = false;
                            System.out.println("Thank you for using Employee System!");
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter 1-6.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a number (1-6), not text!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add New Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee by Name");
        System.out.println("4. Update Employee Age");
        System.out.println("5. Delete Employee");
        System.out.println("6. Exit");
    }

    private static void addEmployee() throws SQLException {
        System.out.println("\n--- Add New Employee ---");

        System.out.print("Enter employee name (Pavan or Pava): ");
        String name = scanner.nextLine().trim();

        if (!name.equalsIgnoreCase("Pavan") && !name.equalsIgnoreCase("Pava")) {
            System.out.println("❌ Only 'Pavan' or 'Pava' are allowed!");
            return;
        }

        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter age: ");
        String ageInput = scanner.nextLine().trim();
        int age;
        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid age! Please enter a number.");
            return;
        }

        Employee employee = new Employee(name, email, age);
        employeeService.addEmployee(employee);
    }

    private static void showAllEmployees() throws SQLException {
        List<Employee> employees = employeeService.getAllEmployees();

        System.out.println("\n=== All Employees ===");
        if (employees.isEmpty()) {
            System.out.println("No employees found in database.");
        } else {
            System.out.printf("%-4s %-10s %-20s %-4s%n", "ID", "Name", "Email", "Age");
            System.out.println("----------------------------------------");
            for (Employee emp : employees) {
                System.out.printf("%-4d %-10s %-20s %-4d%n",
                        emp.getId(), emp.getName(), emp.getEmail(), emp.getAge());
            }
        }
    }

    private static void searchEmployee() throws SQLException {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine().trim();

        List<Employee> employees = employeeService.getEmployeesByName(name);

        System.out.println("\n=== Search Results for '" + name + "' ===");
        if (employees.isEmpty()) {
            System.out.println("No employees found with that name.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp);
            }
        }
    }

    private static void updateEmployeeAge() throws SQLException {
        System.out.print("\nEnter employee name to update: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter new age: ");
        String ageInput = scanner.nextLine().trim();
        int newAge;
        try {
            newAge = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid age! Please enter a number.");
            return;
        }

        employeeService.updateEmployeeAge(name, newAge);
    }

    private static void deleteEmployee() throws SQLException {
        System.out.print("\nEnter employee name to delete: ");
        String name = scanner.nextLine().trim();

        employeeService.deleteEmployee(name);
    }
}