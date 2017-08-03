package bj.my.blog.blog.start;

import bj.my.blog.blog.start.config.RedisConfig;
import bj.my.blog.blog.start.dao.IUserDao;
import bj.my.blog.blog.start.model.User;
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

    @Test
    public void contextLoads() {

        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.set("w".getBytes(), "g".getBytes());
                return true;
            }
        });

    }

}
