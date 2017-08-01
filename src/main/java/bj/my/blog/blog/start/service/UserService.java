package bj.my.blog.blog.start.service;

import bj.my.blog.blog.start.dao.IUserDao;
import bj.my.blog.blog.start.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserDao userDao;
    public List<User> getAllUser(){
        return userDao.selectAllUser();
    }
}
