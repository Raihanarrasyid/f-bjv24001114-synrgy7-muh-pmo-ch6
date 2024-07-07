package com.binar.binarchallenge4.service.auth;

import com.binar.binarchallenge4.entity.auth.AuthUser;
import com.binar.binarchallenge4.repository.auth.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Oauth2UserDetailsService implements UserDetailsService{

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser user = userRepository.findOneByUsername(s);
        if (null == user) {
            throw new UsernameNotFoundException(String.format("Username %s is not found", s));
        }

        return user;
    }

    @CacheEvict("oauth_username")
    public void clearCache(String s) {
    }
}
