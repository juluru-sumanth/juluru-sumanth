package com.ems.service;

import com.ems.entity.Employee;

import java.util.List;


public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Long saveEmployee(Employee employee);
    public Employee getEmployeById(Integer id);
    public void deleteById(Integer id);
    public  boolean isExist(Integer id);
    public List<Employee> getByFirstName(String firstName);
}
