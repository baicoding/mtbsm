package org.msgtu.sys.service.impl;

import org.msgtu.sys.entry.User;
import org.msgtu.sys.mapper.relational.UserMapper;
import org.msgtu.sys.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Wonshine on 2017-07-07.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public User findByUserLogin(String userlogin) {
        return mapper.findByUserLogin(userlogin);
    }
}
