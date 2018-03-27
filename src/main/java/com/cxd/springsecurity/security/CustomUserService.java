package com.cxd.springsecurity.security;

import com.cxd.springsecurity.dao.SysUserRepository;
import com.cxd.springsecurity.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created with IntelliJ IDEA.
 * User: cxd
 * Date: 2017/11/25
 * Description:
 */
public class CustomUserService implements UserDetailsService{

    @Autowired
    SysUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        SysUser user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }
}
