package com.java.basics.controller;

import com.java.basics.model.Employee;
import com.java.basics.response.QueryResponse;
import com.java.basics.response.Response;
import com.java.basics.response.ResponseHandler;
import com.java.basics.response.ResponsePercent;
import com.java.basics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/home/emp")
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/empPost")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody Employee employee)
    {
        Employee employee1 = employeeService.create(employee);
        Response response = new Response("Data is Created..!!",employee1);
        return ResponseHandler.generateResponse("Data Creation = Success",true, HttpStatus.CREATED,response);
    }
    @PutMapping("/empPut/{emp}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> update(@PathVariable long emp,@RequestBody Employee employee)
    {
        Employee employee1 = employeeService.update(emp,employee);
        Response response = new Response("Data is Updated..!!",employee1);
        return ResponseHandler.generateResponse("Data Updation = Success",true, HttpStatus.OK,response);
    }
//    @GetMapping("/empGet")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public ResponseEntity<Page<Employee>> read(@RequestParam(defaultValue = "0") Integer pageNo,
//                                               @RequestParam(defaultValue = "5") Integer pageSize,
//                                               Sort sort)
//    {
//        Page<Employee> employee = employeeService.read(pageNo,pageSize,sort);
//        return new ResponseEntity<>(employee,HttpStatus.FOUND);
//    }

    @GetMapping("/empGet")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Page<Employee>> read(@RequestParam(defaultValue = "0") Integer pageNo,
                                               @RequestParam(defaultValue = "5") Integer pageSize,
                                               Sort sort)
    {
        Page<Employee> employee = employeeService.read(pageNo,pageSize,sort);
        return new ResponseEntity<>(employee,HttpStatus.FOUND);
    }


    @DeleteMapping("/empDelete/{emp}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable long emp)
    {
        employeeService.delete(emp);
        Response response = new Response("Data is Deleted..!!","Success..!!");
        return ResponseHandler.generateResponse("Data Deletion = Success",true, HttpStatus.GONE,response);
    }

    @GetMapping("/ageLevelSalarySpecified")
    @PreAuthorize("hasRole('ADMIN')")
    public List<QueryResponse> ageLevelSalarySpecified(@RequestParam String age, @RequestParam String deptLevel)
    {
        return employeeService.ageLevelSalaryQueryCreation(age,deptLevel);
    }
    @GetMapping("/getDeptStrengthAtEachLevel")
    @PreAuthorize("hasRole('ADMIN')")
    public List<ResponsePercent> getDeptStrength()
    {
        return employeeService.findDeptStrengthAtEveryLevel();
    }
}
