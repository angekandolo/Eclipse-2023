package com.perscholas.capstone.service;
import com.perscholas.capstone.entity.*;
import com.perscholas.capstone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // find user by email
        User user = userRepository.findByEmail(usernameOrEmail);

        // if not found by email find by username
        if (user == null) {
            user = userRepository.findByUsername(usernameOrEmail);
        }

        if (user != null) {
            return new CustomUserDetails(user);
        } else {
            throw new UsernameNotFoundException("Invalid email or username");
        }
    }
    
    public class CustomUserDetails extends org.springframework.security.core.userdetails.User implements UserDetails {

        private final String name;
        private final String email;
        private final User user;
        //private final String username; 

        public CustomUserDetails(User user) {
            super(user.getUsername(), user.getPassword(), user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList()));
            this.name = user.getName();
            this.email = user.getEmail();
            this.user = user;
            //this.username = user.getUsername();
        }

        public String getName() {
            return name;
        }
        public boolean isAdmin() {
            return getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        }

        public String getEmail() {
            return email;
        }
        public User getUser() {
            return user;
        }

        /*public String getUsername() {
            return username;
        }*/
    }
    public CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (CustomUserDetails) authentication.getPrincipal();
    }

}
