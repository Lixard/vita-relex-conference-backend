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
            "SELECT ev.user_id, ev.event_id, ev.deleted " +
            "FROM event_visitors ev " +
            "JOIN users u ON ev.user_id = u.user_id " +
            "JOIN events e ON ev.event_id = e.event_id " +
            "WHERE NOT (ev.deleted OR u.deleted OR e.deleted) " +
            "AND ev.event_id = #{eventId}"
    )
    List<EventVisitor> getVisitorsByEventId(@Param("eventId") int eventId);

    @Select(
            //language=PostgreSQL
            "SELECT ev.user_id, ev.event_id, ev.deleted " +
            "FROM event_visitors ev " +
            "JOIN users u ON ev.user_id = u.user_id " +
            "JOIN events e ON ev.event_id = e.event_id " +
            "WHERE NOT (ev.deleted OR u.deleted OR e.deleted) " +
            "AND ev.user_id = #{visitorId}"
    )
    List<EventVisitor> getScheduleOfUser(@Param("visitorId") int visitorId);

    @Update(
            //language=PostgreSQL
            "UPDATE event_visitors SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void delete(@Param("userId") int userId, @Param("eventId") int eventId);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO event_visitors(user_id, event_id) " +
            "VALUES (#{userId}, #{eventId})"
    )
    void insert(EventVisitor eventVisitor);

    @Update(
            //language=PostgreSQL
            "UPDATE event_visitors SET deleted = 'false' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void resurrect(@Param("userId") int userId, @Param("eventId") int eventId);
}
