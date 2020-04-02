package ru.relex.db.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ru.relex.db.model.SecurityUserDetails;

@Mapper
public interface UserSecurityMapper {

    @Select(
            //language=PostgreSQL
            "SELECT user_id, username, password, role " +
            "FROM users " +
            "WHERE username = #{username}"
    )
    SecurityUserDetails findUserByUsername(@Param("username") String username);
}
