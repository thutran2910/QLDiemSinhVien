package com.tmt.service.impl;

import com.tmt.pojo.NguoiDung;
import com.tmt.repository.NguoiDungRepository;
import com.tmt.service.NguoiDungService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NguoiDungServiceImpl implements NguoiDungService {

    @Autowired
    private NguoiDungRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public NguoiDung getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung u = this.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Invalid User!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getUserRole()));
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }
}
