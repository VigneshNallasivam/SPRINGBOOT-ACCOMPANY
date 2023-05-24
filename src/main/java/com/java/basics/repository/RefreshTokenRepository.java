package com.java.basics.repository;

import com.java.basics.model.Employee;
import com.java.basics.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>
{
    Optional<RefreshToken> findByToken(String token);

    @Modifying
    int deleteByEmployee(Employee employee);

}
