package com.java.basics.service;

import com.java.basics.exception.DepartmentException;
import com.java.basics.model.Department;
import com.java.basics.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService
{
    @Autowired
    DepartmentRepository departmentRepository;

    public Department create(Department department)
    {
        return departmentRepository.save(department);
    }

    public Department update(long dept,Department department)
    {
        Department department1 = departmentRepository.findById(dept).get();
        if(departmentRepository.findById(dept).isPresent())
        {
            department1.setDeptName(department.getDeptName());
            department1.setDeptStrength(department.getDeptStrength());
            department1.setDeptLevel(department.getDeptLevel());
            departmentRepository.save(department1);
            return department1;
        }
        else
        {
            throw new DepartmentException("ID_NOT_FOUND");
        }
    }

    public Page<Department> read(int pageNo,int pageSize,String sortBy)
    {
        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Department> list = departmentRepository.findAll(pageable);

        if(departmentRepository.findAll().isEmpty())
        {
            throw new DepartmentException("OOPS..! Empty DATA-BASE...");
        }
        else
        {
            return list;
        }
    }

    public void delete(long dept)
    {
        Department department = departmentRepository.findById(dept).get();
        if(departmentRepository.findById(dept).isPresent())
        {
            departmentRepository.deleteById(dept);
        }
        else
        {
            throw new DepartmentException("ID IS NOT-FOUND..!!(TO BE DELETED)");
        }
    }
    public List<Department> getByDeptLevelAsc()
    {
        return departmentRepository.findDepartmentLevelByAsc();
    }
    public List<Department> getByDeptLevelDesc()
    {
        return departmentRepository.findDepartmentLevelByDesc();
    }
    public List<Department> findDepartmentSalaryByDesc()
    {
        return departmentRepository.findDepartmentSalaryByDesc();
    }
}
