package com.java.basics.controller;

import com.java.basics.model.Department;
import com.java.basics.response.Response;
import com.java.basics.response.ResponseHandler;
import com.java.basics.service.DepartmentService;
import com.java.basics.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/home/dept")
public class DepartmentController
{
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PaginationService paginationService;
    @PostMapping("/deptPost")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody Department department)
    {
        Department department1 = departmentService.create(department);
        Response response = new Response("Data is Created..!!",department1);
        return ResponseHandler.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,response);
    }

    @PutMapping("/deptPut/{dept}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> update(@PathVariable long dept, @RequestBody Department department)
    {
        Department department1 = departmentService.update(dept,department);
        Response response = new Response("Data is Updated..!!",department1);
        return ResponseHandler.generateResponse("Data Updation = Success",true, HttpStatus.OK,response);
    }
    @GetMapping("/deptGet")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> read(@RequestParam Integer pageNo,
                                       @RequestParam Integer pageSize,
                                       @RequestParam String sortBy)
    {
        Page<Department> department = departmentService.read(pageNo,pageSize,sortBy);
        Response response = new Response("Data is Fetched/Readed..!!",department);
        return ResponseHandler.generateResponse("Data Reading = Success",true, HttpStatus.FOUND,response);
    }
    @DeleteMapping("/deptDelete/{dept}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable long dept)
    {
        departmentService.delete(dept);
        Response response = new Response("Data is Deleted..!!","Success..!!");
        return ResponseHandler.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,response);
    }
    @GetMapping("/deptSortByLevelAsc")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Department> getByLevelAsc()
    {
        return departmentService.getByDeptLevelAsc();
    }
    @GetMapping("/deptSortByLevelDesc")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Department> getByLevelDesc()
    {
        return departmentService.getByDeptLevelDesc();
    }
    @GetMapping("/getByDeptSalaryLevel")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Department> getByDeptSalaryLevels()
    {
        return departmentService.findDepartmentSalaryByDesc();
    }
    @GetMapping("/pageFilterFindByDepartmentName")
    public Page<Department> findByDepartmentName(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "dept") String sortBy,
                                                 @RequestParam(defaultValue = "") String deptName)
    {
        return paginationService.findByDeptName(pageNo,pageSize,sortBy,deptName);
    }
}
