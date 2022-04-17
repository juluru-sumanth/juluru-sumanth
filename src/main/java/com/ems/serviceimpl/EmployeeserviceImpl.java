package com.ems.serviceimpl;

import com.ems.entity.Employee;
import com.ems.exceptions.UserExistingException;
import com.ems.exceptions.UserNotFoundException;
import com.ems.repository.EmployeeRepository;

import com.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
    public Employee saveEmployee(Employee employee) throws UserExistingException {

        Employee existEmployee =  employeeRepository.getById(employee.getId());

        if (existEmployee != null) {
            throw new UserExistingException("User already exist in repository");
        }
        return employeeRepository.save(employee);
    }


    @Override
    public Optional<Employee> getEmployeById(Integer id) throws UserNotFoundException {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
//        if (optional.isPresent()) {
//            employee = optional.get();
//        } else {
//            throw new UserNotFoundException("Employee not found for id " + id);
//        }

        if (!optional.isPresent()) {
            throw new UserNotFoundException("UserNotFound with this id" + id);
        }
        return optional;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Employee> optionalEmployee = this.employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found with given id - " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public boolean isExist(Integer id) {

        return employeeRepository.existsById(id);
    }

    @Override
    public List<Employee> getByFirstName(@PathVariable String firstName) {
        return (List<Employee>) employeeRepository.findByFirstName(firstName);
    }

    @Override
    public Employee getByfName(String fName) {
        return employeeRepository.findByFirstName(fName);
    }

}
