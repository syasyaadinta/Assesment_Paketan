package com.project.Assesment.rest;

import com.project.Assesment.dto.account.RequestTokenDTO;
import com.project.Assesment.dto.account.ResponseTokenDTO;
import com.project.Assesment.service.AccountService;
import com.project.Assesment.utility.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public ResponseTokenDTO post(@RequestBody RequestTokenDTO dto){
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
            authenticationManager.authenticate(token);

            Authentication authentication = authenticationManager.authenticate(token);
            Object thePrincipal = authentication.getPrincipal();
        }catch (Exception exception){
            System.out.println("Can not authenticate!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not authenticate", exception);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        System.out.println("userDetails: " + userDetails.getUsername() + ", " + userDetails.getPassword());

        String role = accountService.getAccountRole(dto.getUsername());
        String token = jwtToken.generateToken(dto.getSubject(),dto.getUsername(), dto.getSecretKey(), role, dto.getAudience());
        ResponseTokenDTO response = new ResponseTokenDTO(dto.getUsername(), role, token);

        return response;
    }
}
