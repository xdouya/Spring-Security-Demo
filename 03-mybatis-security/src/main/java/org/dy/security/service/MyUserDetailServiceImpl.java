package org.dy.security.service;

import lombok.extern.slf4j.Slf4j;
import org.dy.security.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author caiwl
 * @date 2020/8/20 17:06
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public MyUserDetailServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("整合mybatis 查询用户信息");
        return userDao.loadUserByUsername(username);
    }
}
