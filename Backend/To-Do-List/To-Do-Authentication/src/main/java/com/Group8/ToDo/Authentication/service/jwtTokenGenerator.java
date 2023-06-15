package com.Group8.ToDo.Authentication.service;

import com.Group8.ToDo.Authentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class jwtTokenGenerator implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        System.out.println("Get Access to security Token");
        String jwtToken;
        jwtToken= Jwts.builder().setSubject(user.getUserName()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secretKey").compact();
        Map<String,String> map=new HashMap<>();
        map.put("token",jwtToken);
        map.put("message","User Get Access to Login");
        return map;
    }
}
