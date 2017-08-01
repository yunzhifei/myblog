package bj.my.blog.blog.start.dao;

import bj.my.blog.blog.start.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IUserDao {
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name,password,salt";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Select({"select " + SELECT_FIELDS + " from " + TABLE_NAME})
    List<User> selectAllUser();

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELDS + " ) " + "values ( " + "#{name},#{password},#{salt}" + " )"})
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    long addUser(User user);

    @Select({"select " + SELECT_FIELDS + " from " + TABLE_NAME + " where name=#{userName}"})
    User selectUserByUserName(String userName);
}
