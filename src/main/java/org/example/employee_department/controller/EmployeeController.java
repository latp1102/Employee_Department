package org.example.employee_department.controller;


import lombok.RequiredArgsConstructor;
import org.example.employee_department.model.Employee;
import org.example.employee_department.repository.EmployeeRepository;
import org.example.employee_department.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    @GetMapping("/employees")
    public String listEmployees(Model model) {

        model.addAttribute(
                "employees",
                employeeRepository.findAll()
        );
        return "employee-list";
    }
    @GetMapping("/employees/add")
    public String showAddForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute(
                "departments",departmentRepository.findAll()
        );

        return "employee-form";
    }
    @PostMapping("/employees/add")
    public String addEmployee(@RequestParam("file") MultipartFile file, Employee employee, Model model) {
        try {
            if (file != null && !file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String timestamp = String.valueOf(System.currentTimeMillis());
                String newFileName = timestamp + "_" + fileName;

                String uploadDir = "uploads/avatars/";
                java.io.File uploadPath = new java.io.File(uploadDir);
                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }
                java.io.File destination = new java.io.File(uploadPath, newFileName);
                file.transferTo(destination);
                employee.setAvatar(newFileName);
            }
            employeeRepository.save(employee);
            return "redirect:/employees";
        } catch (Exception e) {
            model.addAttribute("error", "Error uploading file: " + e.getMessage());
            model.addAttribute("departments", departmentRepository.findAll());
            return "employee-form";
        }
    }
}