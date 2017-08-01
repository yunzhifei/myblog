package bj.my.blog.blog.start.dao;

import bj.my.blog.blog.start.model.LoginTicket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import sun.rmi.runtime.Log;

import java.util.List;

@Mapper
public interface ILoginTicketDao {
    String TABLE_NAME = " loginTicket ";
    String INSERT_FIELDS = " userId,status,expired,ticket ";
    String SELECT_FIELDS = " id, ";

    @Select({"select " + SELECT_FIELDS + " from " + TABLE_NAME})
    List<LoginTicket> selectLoginTicketsByUserId();

    @Insert({"insert into " + TABLE_NAME + " ( " + INSERT_FIELDS + " )" + " values (" + "#{userId},#{status},#{expired},#{ticket}" + " ) "})
    @Options(useGeneratedKeys = true)
    int addLoginTicket(LoginTicket loginTicket);

    @Select({" select " + SELECT_FIELDS + " from " + TABLE_NAME + "" + "where ticket=#{ticket}"})
    LoginTicket selectLoginTicketByTicket(String ticket);
}
