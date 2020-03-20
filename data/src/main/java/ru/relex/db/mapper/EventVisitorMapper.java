package ru.relex.db.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.relex.db.model.EventVisitor;

import java.util.List;

public interface EventVisitorMapper {
    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, deleted " +
            "FROM event_visitors WHERE event_id = #{eventId} AND NOT deleted"
    )
    List<EventVisitor> allEventVisitors(@Param("eventId") int eventId);
    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, deleted " +
            "FROM event_visitors WHERE user_id = #{visitorId} AND NOT deleted"
    )
    List<EventVisitor> allVisitorEvents(@Param("userId") int userId);

    @Update(
            //language=PostgreSQL
            "UPDATE event_visitors SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void delete(@Param("userId") int userId, @Param("eventId") int eventId);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO event_visitors(user_id, event_id, deleted) " +
            "VALUES (#{userId}, #{eventId}, #{deleted})"
    )
    void insert(EventVisitor eventVisitor);
}
