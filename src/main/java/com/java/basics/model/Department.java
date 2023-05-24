package com.java.basics.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="department_table")
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Department
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dept;//Department_ID
    private String deptName;
    private long deptStrength;
    private String deptLevel;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    @JsonManagedReference
    @OneToOne(mappedBy="department",cascade=CascadeType.ALL)
    private Salary salaried;
}
