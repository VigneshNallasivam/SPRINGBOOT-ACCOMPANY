package com.java.basics.repository;

import com.java.basics.model.Role;
import com.java.basics.security.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer>
{
    Optional<Role> findByName(ERole name);
}
