package dev.dialvive.interview.walmart.exercise2;

import java.util.List;

/*
 * Implement a Java interface called `EmployeeService` that includes the following methods:
 * 1. `List<Employee> getAllEmployees()`: Retrieves a list of all employees.
 * 2. `Employee getEmployeeById(long id)`: Retrieves an employee by their ID.
 * 3. `void addEmployee(Employee employee)`: Adds a new employee to the system.
 */
public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(final long id);

    void addEmployee(final Employee employee);

}
