package bj.my.blog.blog.start;

import bj.my.blog.blog.start.dao.IUserDao;
import bj.my.blog.blog.start.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Autowired
    IUserDao userDao;
    @Autowired
    RedisTemplate<?, ?> redisTemplate;
    @Autowired
    MailService mailService;

    @Test
    public void contextLoads() {
        mailService.sendMailSimple("1143047851@qq.com","测试","测试服务！");

    }

}
