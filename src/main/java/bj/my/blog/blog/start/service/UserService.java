package bj.my.blog.blog.start.service;

import bj.my.blog.blog.start.dao.IUserDao;
import bj.my.blog.blog.start.model.User;
import bj.my.blog.blog.start.util.EncryptAndDecodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserDao userDao;
    public List<User> getAllUser(){
        return userDao.selectAllUser();
    }
    /**
     * 根据用户输入的用户名和密码来判断用户名和密码是否匹配
     * */
    public String doLogin(String userName, String password, HashMap<String,String> responseMap){
        //判断用户名密码是否符合要求
        if(userName.startsWith("^[0-9]+")){
            responseMap.put("error","用户名不可以以数字开头");
        }
        //密码长度不可以是纯数字或者纯字母必须有特殊字符
        if(!(password.matches(".*?[^a-zA-Z\\d]+.*?")
                    && password.matches(".*?[a-z]+.*?")
                && password.matches(".*?[A-Z]+.*?")
                && password.matches(".*?[\\d]+.*?"))){
            responseMap.put("error","");
        }
        //用户名和密码都符合要求了可以验证密码和用户名了
        //解密前端传来的字符串
        // TODO: 2017/8/1 前端加密
        User user = userDao.selectUserByUserName(userName);
        if(null != user && EncryptAndDecodeUtil.MD5(password+user.getSalt()).equals(password)){
            //登录成功添加ticket，
        }
        return "";
    }

    private String addLoginTicket(){

        return "";
    }
}
