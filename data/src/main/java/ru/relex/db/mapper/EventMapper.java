package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.Event;

import java.util.List;

@Mapper
public interface EventMapper {

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT * FROM events WHERE event_id = #{id})"
    )
    boolean isEventExists(@Param("id") int id);

    @Select(
            //language=PostgreSQL
            "SELECT " +
            "e.event_id, " +
            "e.event_name, " +
            "e.event_type, " +
            "e.conference_id, " +
            "e.html_description, " +
            "e.location, " +
            "e.time_start, " +
            "e.time_end, " +
            "e.created_by, " +
            "e.deleted " +
            "FROM events e " +
            "JOIN conferences c ON e.conference_id = c.conference_id " +
            "WHERE NOT (e.deleted OR c.deleted)"
    )
    List<Event> getEvents();

    @Select(
            //language=PostgreSQL
            "SELECT " +
            "e.event_id, " +
            "e.event_name, " +
            "e.event_type, " +
            "e.conference_id, " +
            "e.html_description, " +
            "e.location, " +
            "e.time_start, " +
            "e.time_end, " +
            "e.created_by, " +
            "e.deleted " +
            "FROM events e " +
            "JOIN conferences c ON e.conference_id = c.conference_id " +
            "WHERE NOT (e.deleted OR c.deleted) AND e.conference_id = #{conferenceId}"
    )
    List<Event> getEventsByConferenceId(@Param("conferenceId") int conference_id);

    @Select(
            //language=PostgreSQL
            "SELECT " +
            "e.event_id, " +
            "e.event_name, " +
            "e.event_type, " +
            "e.conference_id, " +
            "e.html_description, " +
            "e.location, " +
            "e.time_start, " +
            "e.time_end, " +
            "e.created_by, " +
            "e.deleted " +
            "FROM events e " +
            "JOIN conferences c ON e.conference_id = c.conference_id " +
            "WHERE NOT (e.deleted OR c.deleted) AND e.event_id = #{id}"
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
            "time_end = #{timeEnd} " +
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
            "location, time_start, time_end, created_by) " +
            "VALUES (#{eventName}, #{eventType}, #{conferenceId}, #{htmlDescription}, #{location}, " +
            "#{timeStart}, #{timeEnd}, #{createdBy})"
    )
    @SelectKey(
            before = false,
            keyProperty = "eventId",
            keyColumn = "event_id",
            statement = "select currval('events_event_id_seq')",
            resultType = Integer.class
    )
    void insert(Event event);

    @Update(
            //language=PostgreSQL
            "UPDATE events SET deleted = 'false' WHERE event_id = #{id}"
    )
    void resurrect(@Param("id") int id);
}
