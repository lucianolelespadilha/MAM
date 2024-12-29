package com.llp.mam.controller;


import com.llp.mam.dtos.AuthenticateDto;
import com.llp.mam.entity.User;
import com.llp.mam.security.token.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;



  @PostMapping
  public ResponseEntity<?> authenticateUser(@RequestBody @Valid AuthenticateDto authenticateDto) {

        var token = new UsernamePasswordAuthenticationToken(authenticateDto.userName(), authenticateDto.password());
        var authentication = authenticationManager.authenticate(token);

        return ResponseEntity.ok(tokenService.createToken((User) authentication.getPrincipal()));
    }

}
