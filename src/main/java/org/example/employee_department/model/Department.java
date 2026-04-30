package org.example.employee_department.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Employee> employees;
}
