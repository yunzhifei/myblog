package bj.my.blog.blog.start;

import bj.my.blog.blog.start.dao.IUserDao;
import bj.my.blog.blog.start.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    IUserDao userDao;
    @Test
    public void contextLoads() {

    }

}
