package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select(
            "SELECT " +
                    "user_id, " +
                    "username, " +
                    "first_name, " +
                    "last_name, " +
                    "email, " +
                    "password, " +
                    "role, " +
                    "deleted " +
                    "FROM users " +
                    "WHERE (#{search:VARCHAR} IS NULL " +
                    "OR CONCAT_WS('$', first_name, last_name, username) LIKE CONCAT('%', #{search:VARCHAR}, '%')) " +
                    "AND NOT deleted"
    )
    List<User> getUsers(@Param("search") String search);

    @Select(
            "SELECT " +
                "user_id, " +
                "username, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "password, " +
                "role, " +
                "deleted " +
                "FROM users " +
                "WHERE user_id = #{id} AND NOT deleted"
    )
    User findById(@Param("id") int id);

    @Update(
            "UPDATE users" +
                    "SET username = #{username}, " +
                    "first_name = #{firstName}, " +
                    "last_name = #{lastName}, " +
                    "email = #{email}, " +
                    "password = #{password}, " +
                    "role = #{role}, " +
                    "deleted = #{deleted} " +
                    "WHERE user_id = #{userId}"
    )
    void update(User user);

    @Update(
            "UPDATE users SET deleted = 'true' WHERE user_id = #{id}"
    )
    void delete(@Param("id") int id);

    @Insert(
            "INSERT INTO users(" +
                    "user_id, " +
                    "username, " +
                    "first_name, " +
                    "last_name, " +
                    "email, " +
                    "password, " +
                    "role, " +
                    "deleted) " +
                    "VALUES " +
                    "(" +
                    "#{userId}, " +
                    "#{username}, " +
                    "#{firstName}, " +
                    "#{lastName}, " +
                    "#{email}, " +
                    "#{password}, " +
                    "#{role}, " +
                    "#{deleted}) "
    )
    @SelectKey(
            before = false,
            keyProperty = "userId",
            keyColumn = "user_id",
            statement = "select currval('users_user_id_seq')",
            resultType = Integer.class)
    void insert(User user);
}
