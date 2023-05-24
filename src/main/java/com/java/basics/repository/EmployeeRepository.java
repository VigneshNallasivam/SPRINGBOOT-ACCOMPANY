package com.java.basics.repository;

import com.java.basics.model.Employee;
import com.java.basics.response.QueryResponse;
import com.java.basics.response.ResponsePercent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    Optional<Employee> findByName(String name);

    Optional<Employee> findByGender(String gender);

    Optional<Employee> findByAge(String age);

    Optional<Employee> findByDomain(String domain);

    Optional<Employee> findByDesignation(String designation);

    Optional<Employee> findByAddress(String address);

    Optional<Employee> findByMobile(String mobile);

    Optional<Employee> findByMail(String mail);

    Optional<Employee> findByPassword(String password);

    Boolean existsByName(String name);

    Boolean existsByMail(String mail);

    @Query(value = "SELECT NEW com.java.basics.response.QueryResponse(e.age,e.department.deptLevel,e.salary.salaryAmount)FROM Employee e WHERE e.age > ?1 AND e.department.deptLevel > ?2 AND e.salary.salaryAmount BETWEEN 25000.00 AND 50000.00")
    List<QueryResponse> ageLevelSalaryQuery(String age, String deptLevel);

//    @Query(value = "SELECT NEW com.java.basics.response.ResponsePercent(d.deptName, COUNT(e.emp) * 100.0 / (SELECT COUNT(e.emp) FROM Employee e))" + " FROM Employee e JOIN e.department d GROUP BY d.deptName")
//    List<ResponsePercent> percentageOFEmployeeInDepartment();

    @Query(value = "SELECT NEW com.java.basics.response.ResponsePercent(d.deptName, COUNT(e.emp) * 100.0 / (SELECT COUNT(e.emp) FROM Employee e))" + " FROM Employee e JOIN e.department d GROUP BY d.deptName ORDER BY (COUNT(e.emp) * 100.0 / (SELECT COUNT(e.emp) FROM Employee e)) DESC")
    List<ResponsePercent> percentageOFEmployeeInDepartment();

}
