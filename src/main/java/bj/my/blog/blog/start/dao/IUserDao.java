package bj.my.blog.blog.start.dao;

import bj.my.blog.blog.start.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IUserDao {
    String TABLE_NAME = " user ";
    String INSERT_FILEDS = " name,password,salt";
    String SELECT_FIELDS = " id, " + INSERT_FILEDS;

    @Select({"select " + SELECT_FIELDS + " from " + TABLE_NAME   })
    List<User> selectAllUser();

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FILEDS + " ) " + "values ( " + "#{name},#{password},#{salt}" +" )"})
    int addUser(User user);
}
