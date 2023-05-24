package com.java.basics.controller;

import com.java.basics.model.Salary;
import com.java.basics.response.Response;
import com.java.basics.response.ResponseHandler;
import com.java.basics.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home/salary")
public class SalaryController
{
    @Autowired
    SalaryService salaryService;
    @PostMapping("/salaryPost")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody Salary salary)
    {
        Salary salary1 = salaryService.create(salary);
        Response response = new Response("Data is Created..!!",salary1);
        return ResponseHandler.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,response);
    }

    @PutMapping("/salaryPut/{salary}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> update(@PathVariable long salary,@RequestBody Salary salaries)
    {
        Salary salary1 = salaryService.update(salary,salaries);
        Response response = new Response("Data is Updated..!!",salary1);
        return ResponseHandler.generateResponse("Data Updation = Success",true, HttpStatus.OK,response);
    }
    @GetMapping("/salaryGet")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Object> read(Integer pageNo,Integer pageSize,String sortBy)
    {
        Page<Salary> salary = salaryService.read(pageNo,pageSize,sortBy);
        Response response = new Response("Data is Fetched/Readed..!!",salary);
        return ResponseHandler.generateResponse("Data Reading = Success",true, HttpStatus.FOUND,response);
    }
    @DeleteMapping("/salaryDelete/{salary}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable long salary)
    {
        salaryService.delete(salary);
        Response response = new Response("Data is Deleted..!!","Success..!!");
        return ResponseHandler.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,response);
    }
}
