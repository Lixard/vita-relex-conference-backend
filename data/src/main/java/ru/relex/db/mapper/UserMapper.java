package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT * FROM users WHERE user_id = #{id})"
    )
    boolean isUserExists(@Param("id") int id);

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT * FROM users WHERE username = #{username})"
    )
    boolean isUsernameExists(@Param("username") String username);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                     "user_id, " +
                    "username, " +
                    "first_name, " +
                    "last_name, " +
                    "email, " +
                    "password, " +
                    "role, " +
                    "deleted, " +
                    "link_image "+
                    "FROM users " +
                    "WHERE (#{search:VARCHAR} IS NULL " +
                    "OR CONCAT_WS('$', first_name, last_name, username) LIKE CONCAT('%', #{search:VARCHAR}, '%')) " +
                    "AND NOT deleted"
    )
    List<User> getAllUsersBySearchOptions(@Param("search") String search);

    @Select(
            //language=PostgreSQL
            "SELECT " +
                    "user_id, " +
                    "username, " +
                    "first_name, " +
                    "last_name, " +
                    "email, " +
                    "password, " +
                    "role, " +
                    "deleted, " +
                    "link_image "+
                    "FROM users " +
                    "WHERE (#{search:VARCHAR} IS NULL " +
                    "OR CONCAT_WS('$', first_name, last_name, username) LIKE CONCAT('%', #{search:VARCHAR}, '%')) " +
                    "AND NOT deleted"
    )
    List<User> getAllUsers();
    @Select(
            //language=PostgreSQL
            "SELECT " +
                "user_id, " +
                "username, " +
                "first_name, " +
                "last_name, " +
                "email, " +
                "password, " +
                "role, " +
                "deleted, " +
                "link_image" +
                "FROM users " +
                "WHERE user_id = #{id} AND NOT deleted"
    )
    User findById(@Param("id") int id);

    @Update(
            //language=PostgreSQL
            "UPDATE users " +
                    "SET username = #{username}, " +
                    "first_name = #{firstName}, " +
                    "last_name = #{lastName}, " +
                    "email = #{email}, " +
                    "password = #{password}, " +
                    "role = #{role} " +
                    "WHERE user_id = #{userId}"
    )
    void update(User user);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO users(" +
                    "username, " +
                    "first_name, " +
                    "last_name, " +
                    "email, " +
                    "password, " +
                    "role) " +
                    "VALUES " +
                    "(" +
                    "#{username}, " +
                    "#{firstName}, " +
                    "#{lastName}, " +
                    "#{email}, " +
                    "#{password}, " +
                    "#{role})"
    )
    @SelectKey(
            before = false,
            keyProperty = "userId",
            keyColumn = "user_id",
            statement = "select currval(pg_get_serial_sequence('users', 'user_id'))",
            resultType = Integer.class
    )
    void insert(User user);

    @Update(
            //language=PostgreSQL
            "UPDATE users SET deleted = 'true' WHERE user_id = #{id}"
    )
    void delete(@Param("id") int id);

    @Update(
            //language=PostgreSQL
            "UPDATE users SET deleted = 'false' WHERE user_id = #{id}"
    )
    void resurrect(@Param("id") int id);

    @Update(
            //language=PostgreSQL
            "UPDATE users SET link_image = #{linkToImage} WHERE user_id = #{id}"
    )
    void updateUserImage(@Param("id") int id, @Param("linkToImage") String linkToImage);
}
