/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tmt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tmt.pojo.User;
import com.tmt.repository.UserRepository;
import com.tmt.service.UserService;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public User getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        // Cấp quyền cho người dùng
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        
        // Trả về thông tin người dùng cho Spring Security
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        User user = this.getUserByUsername(username);
        return user != null && this.passEncoder.matches(password, user.getPassword());
    }

    @Override
    @Transactional
    public User addUser(Map<String, String> params, MultipartFile avatar) {
        User existingUser = this.getUserByUsername(params.get("username"));
        if (existingUser != null) {
            throw new RuntimeException("Username already exists");
        }

        User u = new User();
        u.setFirstName(params.get("firstName"));
        u.setLastName(params.get("lastName"));
        u.setPhone(params.getOrDefault("phone", "9999999999"));
        u.setEmail(params.getOrDefault("email", "a@gmail.com"));
        u.setUsername(params.get("username"));
        u.setPassword(this.passEncoder.encode(params.get("password")));
        u.setUserRole("ROLE_USER");

        if (!avatar.isEmpty()) {
            try {
                Map<String, Object> uploadResult = this.cloudinary.uploader().upload(avatar.getBytes(), 
                        ObjectUtils.asMap("resource_type", "auto"));
                u.setAvatar(uploadResult.get("secure_url").toString());
            } catch (IOException ex) {
                throw new RuntimeException("Failed to upload avatar", ex);
            }
        }
        
        this.userRepo.addUser(u);
        return u;
    }
}
