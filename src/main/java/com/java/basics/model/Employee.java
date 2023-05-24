package com.java.basics.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employee_table")
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long emp;//Employee_ID
    private String name;
    private String gender;
    private String age;
    private String domain;
    private String designation;
    private String address;
    private String mobile;
    private String mail;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "employees_roles", joinColumns = @JoinColumn(name = "emp"), inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="dept")
    Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="salary")
    Salary salary;

    public Employee(String name,String mail,String password)
    {
        this.name=name;
        this.mail=mail;
        this.password=password;
    }
}

