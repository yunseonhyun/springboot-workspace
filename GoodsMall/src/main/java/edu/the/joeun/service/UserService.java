package edu.the.joeun.service;

import edu.the.joeun.mapper.UserMapper;
import edu.the.joeun.model.Goods;
import edu.the.joeun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.getAll();
    }

}
