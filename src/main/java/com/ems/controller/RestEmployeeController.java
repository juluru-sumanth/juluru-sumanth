package com.ems.controller;

import com.ems.entity.Employee;

import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import com.ems.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class RestEmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PlayerService playerService;

    @GetMapping("/getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();

    }


    @PostMapping("/saveEmp")
    public String saveEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        return "Hi " + employee.getFirstName() + "Your registration successfull";

    }


    @GetMapping("/getByName/{firstName}")
    public List<Employee> getByName(@PathVariable("firstName") String firstName) {
        return employeeService.getByFirstName(firstName);

    }

    @GetMapping("/getById/{id}")
    public ResponseEntity <String>getStudent(@PathVariable("id") Long id) {

        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeById(id));

        ResponseEntity response = null;

        if (employee.isPresent()) {
            response = new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("Employee not found for id" + id, HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        ResponseEntity response = null;
        Long id = employee.getId();
        if (employeeService.isExist(id)) {
            Long uId = employeeService.saveEmployee(employee);
            response = new ResponseEntity<String>("update succesfull for id" + id, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("Data not found for id " + id, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/deletById/{id}")
    public List<Employee> deleteEmp(@PathVariable Long id) {
        employeeService.deleteById(id);
        return employeeService.getAllEmployees();

    }

}
