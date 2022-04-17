package com.ems.service;

import com.ems.entity.Employee;
import com.ems.exceptions.UserExistingException;
import com.ems.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    public List<Employee> getAllEmployees();
    public Employee saveEmployee(Employee employee) throws UserExistingException;
    public Optional<Employee> getEmployeById(Integer id) throws UserNotFoundException;
    public void deleteById(Integer id) throws UserNotFoundException;
    public  boolean isExist(Integer id);
    public  List<Employee> getByFirstName(String firstName);

    public  Employee getByfName(String fName);
}
