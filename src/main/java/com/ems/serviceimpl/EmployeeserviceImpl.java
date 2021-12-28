package com.ems.serviceimpl;

import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepository;

import com.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeserviceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public Long saveEmployee(Employee employee) {

        this.employeeRepository.save(employee);
        return null;
    }




    @Override
    public Employee getEmployeById(Long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id " + id);
        }
        return employee;
    }

    @Override
    public void deleteById(Long id) {
        this.employeeRepository.deleteById(id);

    }

    @Override
    public boolean isExist(Long id) {

        return employeeRepository.existsById(id);
    }

    @Override
    public List<Employee> getByFirstName(@PathVariable String firstName) {
        return (List<Employee>) employeeRepository.findByFirstName(firstName);
    }

}
