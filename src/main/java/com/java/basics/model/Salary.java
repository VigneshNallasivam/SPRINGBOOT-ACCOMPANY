package com.java.basics.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity
@Table(name = "salary_table")
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Salary
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long salary;//Employee_ID
    private String accNo;
    private String salaryBankAccName;
    private String salaryBankIfsc;
    private String salaryPackage;
    private String salaryDate;
    private double salaryAmount;
    private String EPF;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Employee> employeeList;


    @JsonBackReference
    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="dept",referencedColumnName="dept")
    private Department department;

}
