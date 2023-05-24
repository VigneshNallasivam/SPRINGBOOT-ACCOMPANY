package com.java.basics.repository;

import com.java.basics.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary,Long>
{
      Optional<Salary> findByAccNo(String accNo);
      Optional<Salary> findBySalaryBankAccName(String salaryBankAccName);
      Optional<Salary> findBySalaryBankIfsc(String salaryBankIfsc);
      Optional<Salary> findBySalaryPackage(String salaryPackage);
      Optional<Salary> findBySalaryDate(String salaryDate);
      Optional<Salary> findBySalaryAmount(String salaryAmount);
      Optional<Salary> findByEPF(String EPF);
}
