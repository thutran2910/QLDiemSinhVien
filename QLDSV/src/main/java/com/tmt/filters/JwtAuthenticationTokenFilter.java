/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.filters;

import com.tmt.pojo.NguoiDung;
import com.tmt.components.JwtService;
import com.tmt.service.NguoiDungService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private final static String TOKEN_HEADER = "Authorization";
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private NguoiDungService userService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String authToken = httpRequest.getHeader(TOKEN_HEADER);
        
        if (authToken == null || !authToken.startsWith("Bearer ")) {
            System.out.println("No Bearer token found in request header");
            chain.doFilter(request, response);
            return;
        }

        authToken = authToken.substring(7); // Remove "Bearer " prefix
        
        if (jwtService.validateTokenLogin(authToken)) {
            try {
                String username = jwtService.getUsernameFromToken(authToken);
                System.out.println("Token validated successfully. Username: " + username);
                
                NguoiDung user = userService.getUserByUsername(username);
                if (user != null) {
                    System.out.println("User found: " + username);
                    
                    boolean enabled = true;
                    boolean accountNonExpired = true;
                    boolean credentialsNonExpired = true;
                    boolean accountNonLocked = true;
                    
                    Set<GrantedAuthority> authorities = new HashSet<>();
                    authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
                    
                    UserDetails userDetail = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                            enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
                    
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                            null, userDetail.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("User authenticated successfully");
                } else {
                    System.out.println("User not found for username: " + username);
                }
            } catch (Exception e) {
                System.out.println("Error while authenticating user: " + e.getMessage());
            }
        } else {
            System.out.println("Token validation failed for token: " + authToken);
        }
        
        chain.doFilter(request, response);
    }
}
