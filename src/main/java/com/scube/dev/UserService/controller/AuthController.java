package com.scube.dev.UserService.controller;

import com.scube.dev.UserService.entity.Role;
import com.scube.dev.UserService.entity.User;
import com.scube.dev.UserService.payload.JWTAuthResponse;
import com.scube.dev.UserService.payload.LoginDto;
import com.scube.dev.UserService.payload.RoleDto;
import com.scube.dev.UserService.payload.SignUpDto;
import com.scube.dev.UserService.repository.RoleRepository;
import com.scube.dev.UserService.repository.UserRepository;
import com.scube.dev.UserService.security.JwtTokenProvider;
import com.scube.dev.UserService.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        //get token from tokenprovider
        String token = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponse(token));

    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto){

        //add check for username already exists in DB
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            return new ResponseEntity<>("Username is already Taken!", HttpStatus.BAD_REQUEST);
        }

        //add check for email already exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already exists!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        List<RoleDto> roles = roleService.getAllRole();

        //if there is no user exists create 1st user as Admin after creating some general roles
        if(roles.isEmpty()){

            //creating a new role Admin
            RoleDto roleDto = new RoleDto();
            roleDto.setRoleName("ROLE_ADMIN");
            roleService.saveRole(roleDto);

            //creating a new role for general Users
            RoleDto roleDto1 = new RoleDto();
            roleDto1.setRoleName("ROLE_USER");
            roleService.saveRole(roleDto1);

            Role role = roleRepository.findByName("ROLE_ADMIN").get();
            user.setRoles(Collections.singleton(role));

            userRepository.save(user);

        }else{

            Role role = roleRepository.findByName("ROLE_USER").get();
            user.setRoles(Collections.singleton(role));

            userRepository.save(user);
        }

        return new ResponseEntity<>("User registerd", HttpStatus.OK);

    }

}
