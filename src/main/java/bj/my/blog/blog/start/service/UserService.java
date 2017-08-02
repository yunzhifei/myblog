package bj.my.blog.blog.start.service;

import bj.my.blog.blog.start.dao.ILoginTicketDao;
import bj.my.blog.blog.start.dao.IUserDao;
import bj.my.blog.blog.start.model.LoginTicket;
import bj.my.blog.blog.start.model.User;
import bj.my.blog.blog.start.util.EncryptAndDecodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    IUserDao userDao;
    @Autowired
    ILoginTicketDao loginTicketDao;

    public List<User> getAllUser() {
        return userDao.selectAllUser();
    }

    /**
     * 根据用户输入的用户名和密码来判断用户名和密码是否匹配
     */
    public HashMap<String, Object> doLogin(String userName, String password) {
        HashMap<String, Object> responseMap = new LinkedHashMap<String, Object>();
        //判断用户名密码是否符合要求
        if (userName.startsWith("^[0-9]+")) {
            responseMap.put("error", "用户名不可以以数字开头");
            return responseMap;
        }
        //密码长度不可以是纯数字或者纯字母必须有特殊字符
        if (!(password.matches(".*?[^a-zA-Z\\d]+.*?")
                && password.matches(".*?[a-z]+.*?")
                && password.matches(".*?[A-Z]+.*?")
                && password.matches(".*?[\\d]+.*?"))) {
            responseMap.put("error", "");
            return responseMap;
        }
        //用户名和密码都符合要求了可以验证密码和用户名了
        //解密前端传来的字符串
        // TODO: 2017/8/1 前端加密
        User user = userDao.selectUserByUserName(userName);
        if (null == user) {
            responseMap.put("error", "用户不存在！");
            return responseMap;
        }
        if (!user.getPassword().equals(EncryptAndDecodeUtil.MD5(password + user.getSalt()))) {
            responseMap.put("error", "用户名密码不匹配！");
            return responseMap;
        }
        String ticket = addLoginTicket(user.getUserId());
        responseMap.put("userId", user.getUserId());
        responseMap.put("ticket", ticket);
        return responseMap;
    }

    private String addLoginTicket(long userId) {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        loginTicket.setExpired(date);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replace("-", ""));
        loginTicketDao.addLoginTicket(loginTicket);
        return loginTicket.getTicket();
    }
}
