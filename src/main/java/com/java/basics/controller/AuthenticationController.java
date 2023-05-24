package com.java.basics.controller;

import com.java.basics.entry.Login;
import com.java.basics.entry.Register;
import com.java.basics.model.Employee;
import com.java.basics.model.RefreshToken;
import com.java.basics.model.Role;
import com.java.basics.repository.EmployeeRepository;
import com.java.basics.repository.RoleRepository;
import com.java.basics.security.ERole;
import com.java.basics.security.JwtUtils;
import com.java.basics.security.UserDetailsImpl;
import com.java.basics.security.UserInfoResponse;
import com.java.basics.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.java.basics.response.Message;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/home/auth")
public class AuthenticationController
{
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register)
    {
        if (employeeRepository.existsByName(register.getName())) {
            return ResponseEntity.badRequest().body(new Message("Error: Username is already taken!"));
        }

        if (employeeRepository.existsByMail(register.getMail())) {
            return ResponseEntity.badRequest().body(new Message("Error: Email is already in use!"));
        }

        // Create new user's account
        Employee employee = new Employee(register.getName(), register.getMail(),
                encoder.encode(register.getPassword()));

        List<String> strRoles = register.getRoles();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            System.out.println("null role so user");
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    case "user":
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        employee.setRoles(roles);
        employeeRepository.save(employee);

        return ResponseEntity.ok(new Message("User registered successfully!"));


    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody Login login, HttpServletResponse response)
    {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getName(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        response.setHeader(HttpHeaders.SET_COOKIE, jwtCookie.toString());
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken((int) userDetails.getEmp());

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse((int) userDetails.getEmp(), refreshToken.getToken(), userDetails.getUsername(),
                        userDetails.getMail(), roles));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser()
    {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new Message("You've been signed out!"));
    }

}
