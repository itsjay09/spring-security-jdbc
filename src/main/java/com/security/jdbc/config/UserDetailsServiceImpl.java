package com.security.jdbc.config;

import com.security.jdbc.model.User;
import com.security.jdbc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUserName = userRepository.findByUserName(username);

        byUserName.orElseThrow(()-> new UsernameNotFoundException("not found"+ username));

        return byUserName.map(UserDetailsImpl::new).get()  ;
    }
}
