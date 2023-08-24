package dev.dialvive.interview.walmart.exercise2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Long, Employee> employeeMap = new HashMap<>();

    @Override
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Employee getEmployeeById(long id) {
        return employeeMap.get(id);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getId(), employee);
    }
}
