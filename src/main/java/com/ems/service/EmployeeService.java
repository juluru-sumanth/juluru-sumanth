package com.ems.service;

import com.ems.entity.Employee;

import java.util.List;


public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Long saveEmployee(Employee employee);
    public Employee getEmployeById(Long id);
    public void deleteById(Long id);
    public  boolean isExist(Long id);
    public List<Employee> getByFirstName(String firstName);
}
