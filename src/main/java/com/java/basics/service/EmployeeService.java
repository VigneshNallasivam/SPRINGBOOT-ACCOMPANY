package com.java.basics.service;

import com.java.basics.exception.EmployeeException;
import com.java.basics.model.Employee;
import com.java.basics.repository.EmployeeRepository;
import com.java.basics.response.QueryResponse;
import com.java.basics.response.ResponsePercent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    EmployeeRepository employeeRepository;
    public Employee create(Employee employee)
    {
        return employeeRepository.save(employee);
    }

    public Employee update(long emp,Employee employee)
    {
        Employee employee1 = employeeRepository.findById(emp).get();
        if(employeeRepository.findById(emp).isPresent())
        {
            employee1.setName(employee.getName());
            employee1.setGender(employee.getGender());
            employee1.setAge(employee.getAge());
            employee1.setAddress(employee.getAddress());
            employee1.setMobile(employee.getMobile());
            employee1.setMail(employee.getMail());
            employee1.setPassword(employee.getPassword());
            employeeRepository.save(employee1);
            return employee1;
        }
        else
        {
            throw new EmployeeException("ID_NOT_FOUND");
        }
    }

    public Page<Employee> read(Integer pageNo, Integer pageSize,Sort sort)
    {
        Sort.Order order1 = Sort.Order.asc("name");
        Sort.Order order2 = Sort.Order.desc("age");
        Sort sort1 = Sort.by(order1, order2);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort1);
        Page<Employee> list = employeeRepository.findAll(pageable);
        if(list.hasContent())
        {
                return list;

        }
        else
        {
            throw new EmployeeException("OOPS..! Empty DATA-BASE...");
        }
    }

    public void delete(long emp)
    {
        Employee employee = employeeRepository.findById(emp).get();
        if(employeeRepository.findById(emp).isPresent())
        {
            employeeRepository.deleteById(emp);
        }
        else
        {
            throw new EmployeeException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }

    public List<QueryResponse> ageLevelSalaryQueryCreation(String age,String deptLevel)
    {
          return employeeRepository.ageLevelSalaryQuery(age,deptLevel);
    }
    public List<ResponsePercent> findDeptStrengthAtEveryLevel()
    {
        return employeeRepository.percentageOFEmployeeInDepartment();
    }
}
