package com.ems.controller;


import com.ems.entity.Employee;
import com.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") Long id, Model model) {

        Employee employee = employeeService.getEmployeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";

    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmploye(@PathVariable(value = "id") Long id) {
        this.employeeService.deleteById(id);
        return "redirect:/";
    }
}
