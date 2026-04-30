package org.example.employee_department.config;

import lombok.RequiredArgsConstructor;
import org.example.employee_department.model.Department;
import org.example.employee_department.model.Employee;
import org.example.employee_department.repository.DepartmentRepository;
import org.example.employee_department.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Data implements CommandLineRunner {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    @Override
    public void run(String... args) {
        if (departmentRepository.count() == 0) {
            Department it = new Department(null,"IT","Hanoi",null);
            Department hr = new Department(null,"HR","HCM",null);
            departmentRepository.save(it);
            departmentRepository.save(hr);
            Employee e1 = new Employee(
                    null,
                    "Nguyen Van A",
                    25,
                    "avatar1.png",
                    "ACTIVE",
                    it
            );
            Employee e2 = new Employee(
                    null,
                    "Tran Thi B",
                    28,
                    "avatar2.png",
                    "ACTIVE",
                    hr
            );
            employeeRepository.save(e1);
            employeeRepository.save(e2);
        }
    }
}
