package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.Event;

import java.util.List;

@Mapper
public interface EventMapper {
    @Select(
            //language=PostgreSQL
            "SELECT " +
            "event_id, " +
            "event_name, " +
            "event_type, " +
            "conference_id, " +
            "html_description, " +
            "location, " +
            "time_start, " +
            "time_end, " +
            "created_by, " +
            "deleted " +
            "FROM events WHERE NOT deleted"
    )
    List<Event> getEvents();

    @Select(
            //language=PostgreSQL
            "SELECT " +
            "event_id, " +
            "event_name, " +
            "event_type, " +
            "conference_id, " +
            "html_description, " +
            "location, " +
            "time_start, " +
            "time_end, " +
            "created_by, " +
            "deleted " +
            "FROM events " +
            "WHERE event_id = #{id} AND NOT deleted"
    )
    Event findById(@Param("id") int id);

    @Update(
            //language=PostgreSQL
            "UPDATE events SET " +
            "event_name = #{eventName}, " +
            "event_type = #{eventType}, " +
            "conference_id = #{conferenceId}, " +
            "location = #{location}, " +
            "time_start = #{timeStart}, " +
            "time_end = #{timeEnd}, " +
            "created_by = #{createdBy}, " +
            "deleted = #{deleted} " +
            "WHERE event_id = #{eventId}"
    )
    void update(Event event);

    @Update(
            //language=PostgreSQL
            "UPDATE events SET deleted = 'true' WHERE event_id = #{id}"
    )
    void delete(@Param("id") int id);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO events " +
            "(event_name, event_type, conference_id, html_description, " +
            "location, time_start, time_end, created_by, deleted) " +
            "VALUES (#{eventName}, #{eventType}, #{conferenceId}, #{location}, " +
            "#{timeStart}, #{timeEnd}, #{createdBy}, #{deleted})"
    )
    @SelectKey(
            before = false,
            keyProperty = "eventId",
            keyColumn = "event_id",
            statement = "select currval('events_event_id_seq')",
            resultType = Integer.class
    )
    void insert(Event event);
}
