package com.ems.controller;


import com.ems.entity.Employee;
import com.ems.exceptions.UserExistingException;
import com.ems.exceptions.UserNotFoundException;
import com.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeForm")
    public String showNewEmployeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employe";

    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {

        try {
            employeeService.saveEmployee(employee);
            return "redirect:/";
        }catch (UserExistingException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) throws UserNotFoundException {

        Optional<Employee> employee = employeeService.getEmployeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmploye(@PathVariable(value = "id") Integer id) throws UserNotFoundException {
        this.employeeService.deleteById(id);
        return "redirect:/";
    }
}
