package com.java.basics.service;

import com.java.basics.model.Department;
import com.java.basics.model.Employee;
import com.java.basics.repository.DepartmentRepository;
import com.java.basics.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class PaginationService
{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Employee> getAllPages(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Employee> pagedResult = employeeRepository.findAll(paging);
        if(pagedResult.hasContent())
        {
            return pagedResult.getContent();
        }
        else
        {
            return new ArrayList<Employee>();
        }
    }

    public Page<Department> findByDeptName(Integer pageNo, Integer pageSize, String sortBy, String deptName)
    {
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        return departmentRepository.findByDeptName(pageable,deptName);
    }
}
