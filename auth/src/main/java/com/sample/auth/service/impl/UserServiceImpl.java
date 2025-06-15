package com.sample.auth.service.impl;

import com.sample.auth.dto.LoginDto;
import com.sample.auth.dto.LoginResponse;
import com.sample.auth.dto.UserDto;
import com.sample.auth.entity.User;
import com.sample.auth.mapper.UserMapper;
import com.sample.auth.repository.UserDao;
import com.sample.auth.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper mapper;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Transactional
    public LoginResponse register(UserDto userDto) {
        User user = mapper.mapToEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.password()));
        User savedUser = userDao.save(user);
        return new LoginResponse(generateToken(savedUser.getUsername()), savedUser.getUsername(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginDto loginDto) {
        return userDao.findByUsername(loginDto.username()).map(user -> {
            return new LoginResponse(generateToken(user.getUsername()), user.getUsername(), user.getEmail());
        }).orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
                .compact();
    }
}
