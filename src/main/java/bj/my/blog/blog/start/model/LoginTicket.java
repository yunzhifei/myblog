package bj.my.blog.blog.start.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 用户记录用户是否登录的票据
 */
@Data
@Slf4j
public class LoginTicket {
    private int id;
    private int status; //0 有效 1 无效
    private Date expired;
    private String ticket;
    private long userId;
}
