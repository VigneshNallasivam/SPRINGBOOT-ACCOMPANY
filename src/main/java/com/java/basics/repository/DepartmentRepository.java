package com.java.basics.repository;

import com.java.basics.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>
{
    Optional<Department> findByDeptName(String deptName);
    Optional<Department> findByDeptStrength(long deptStrength);
    Optional<Department> findByDeptLevel(String deptLevel);
    Page<Department> findByDeptName(Pageable pageable, String deptName);
    @Query(value = "SELECT d FROM Department d ORDER BY d.deptLevel ASC")
    List<Department> findDepartmentLevelByAsc();

    @Query(value = "SELECT d FROM Department d ORDER BY d.deptLevel DESC")
    List<Department> findDepartmentLevelByDesc();
    @Query(value = "SELECT d from Department d order by d.salaried.salaryAmount desc")
    List<Department> findDepartmentSalaryByDesc();
}
