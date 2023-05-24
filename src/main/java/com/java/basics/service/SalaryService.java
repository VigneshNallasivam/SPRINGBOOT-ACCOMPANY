package com.java.basics.service;

import com.java.basics.exception.SalaryException;
import com.java.basics.model.Salary;
import com.java.basics.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SalaryService
{
    @Autowired
    SalaryRepository salaryRepository;
    public Salary create(Salary salary)
    {
        return salaryRepository.save(salary);
    }

    public Salary update(long salary,Salary salaries)
    {
        Salary salary1 = salaryRepository.findById(salary).get();
        if(salaryRepository.findById(salary).isPresent())
        {
            salary1.setAccNo(salaries.getAccNo());
            salary1.setSalaryBankAccName(salaries.getSalaryBankAccName());
            salary1.setSalaryBankIfsc(salaries.getSalaryBankIfsc());
            salary1.setSalaryPackage(salaries.getSalaryPackage());
            salary1.setSalaryDate(salaries.getSalaryDate());
            salary1.setEPF(salaries.getEPF());
            salaryRepository.save(salary1);
            return salary1;
        }
        else
        {
            throw new SalaryException("ID_NOT_FOUND");
        }
    }

    public Page<Salary> read(Integer pageNo,Integer pageSize,String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy).descending());
        Page<Salary> list = salaryRepository.findAll(pageable);
        if(salaryRepository.findAll().isEmpty())
        {
            throw new SalaryException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return list;
        }
    }

    public void delete(long salary)
    {
        Salary salaried = salaryRepository.findById(salary).get();
        if(salaryRepository.findById(salary).isPresent())
        {
            salaryRepository.deleteById(salary);
        }
        else
        {
            throw new SalaryException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }
}
