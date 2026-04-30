package org.example.employee_department.controller;


import lombok.RequiredArgsConstructor;
import org.example.employee_department.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    @GetMapping("/employees")
    public String listEmployees(Model model) {

        model.addAttribute(
                "employees",
                employeeRepository.findAll()
        );
        return "employee-list";
    }
}