package com.ems.controller;

import com.ems.entity.Employee;

import com.ems.entity.Player;
import com.ems.exceptions.UserExistingException;
import com.ems.exceptions.UserNotFoundException;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import com.ems.service.PlayerService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
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


//    @GetMapping("/getAll")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//
//        List<Employee> listOfAllEmps = employeeService.getAllEmployees();
//        return new ResponseEntity<List<Employee>>(listOfAllEmps, HttpStatus.OK);
//    }


//    @PostMapping("/saveEmp")
//    public String saveEmployee(@RequestBody Employee employee) {
//        employeeService.saveEmployee(employee);
//        return "Hi " + employee.getFirstName() + "Your registration successfull";
//
//    }

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee) {

        try {
            return employeeService.saveEmployee(employee);
        } catch (UserExistingException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }


//    @GetMapping("/getByName/{firstName}")
//    public List<Employee> getByName(@PathVariable("firstName") String firstName) throws UserNotFoundException {
//        Employee employee= (Employee) employeeService.getByFirstName(firstName);
//
//        if(employee==null){
//            throw new UserNotFoundException("User not found '"+firstName);
//        }
//
//        return (List<Employee>) employee;
//    }

    @GetMapping("/getById/{id}")
    public Optional<Employee> getStudent(@PathVariable("id") Integer id) {

//        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeById(id));
//
//        ResponseEntity response = null;
//
//        if (employee.isPresent()) {
//            response = new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
//        } else {
//            response = new ResponseEntity<String>("Employee not found for id" + id, HttpStatus.BAD_REQUEST);
//        }
//        return response;

        try {
            return employeeService.getEmployeById(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) throws UserExistingException {
        ResponseEntity response = null;
        Integer id = employee.getId();
        if (employeeService.isExist(id)) {
            Employee uId = employeeService.saveEmployee(employee);
            response = new ResponseEntity<String>("update succesfull for id" + id, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("Data not found for id " + id, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Employee> deleteById(@PathVariable int id) throws UserNotFoundException {
        ResponseEntity response = null;
        //Optional optional=Optional.ofNullable(employeeService.getEmployeById(id));
        if (employeeService.isExist(id)) {
            employeeService.deleteById(id);
            response = new ResponseEntity<String>("Employee deleted succesfull for id " + id, HttpStatus.OK);
        } else {
            response = new ResponseEntity<String>("Employee not found for id " + id, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping("/firstName/{fName}")
    public Employee getById(@PathVariable("fName") String fName) throws UserNotFoundException {

        Employee employee=null;
        try {
             employee = (Employee) employeeService.getByfName(fName);

            if (employee == null) {
                throw new UserNotFoundException("User not found '" + fName);
            }
        }catch (Exception  ex){
            throw new UserNotFoundException("User not found ");
        }


        return employee;
        }


}
