package bj.my.blog.blog.start.model;

import lombok.Data;
import lombok.extern.log4j.Log4j;

@Data
@Log4j
public class User {
    int id;
    String name;
    String password;
    String salt;
    long userId;
    String phone;
}
