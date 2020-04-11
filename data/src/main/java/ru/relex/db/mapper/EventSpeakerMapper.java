package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.EventSpeaker;

import java.util.List;

@Mapper
public interface EventSpeakerMapper {

    @Select(
            //language=PostgreSQL
            "SELECT EXISTS(SELECT 1 FROM event_speakers WHERE user_id = #{userId} AND event_id = #{eventId})"
    )
    boolean isExists(@Param("userId") int userId, @Param("eventId") int eventId);

    @Select(
            //language=PostgreSQL
            "SELECT es.user_id, es.event_id, es.created_by, es.created_at, es.deleted " +
            "FROM event_speakers es " +
            "JOIN users u ON es.user_id = u.user_id " +
            "JOIN events e ON es.event_id = e.event_id " +
            "WHERE NOT (es.deleted OR u.deleted OR e.deleted) "+
            "AND es.user_id = #{userId}"
    )
    List<EventSpeaker> getEventsBySpeakerId(@Param("userId") int userId);

    @Select(
            //language=PostgreSQL
            "SELECT es.user_id, es.event_id, es.created_by, es.created_at, es.deleted " +
            "FROM event_speakers es " +
            "JOIN users u ON es.user_id = u.user_id " +
            "JOIN events e ON es.event_id = e.event_id " +
            "WHERE NOT (es.deleted OR u.deleted OR e.deleted) "+
            "AND es.event_id = #{eventId}"
    )
    List<EventSpeaker> getSpeakersByEventId(@Param("eventId") int eventId);

    @Select(
            //language=PostgreSQL
            "SELECT es.user_id, es.event_id, es.created_by, es.created_at, es.deleted " +
            "FROM event_speakers es " +
            "JOIN users u ON es.user_id = u.user_id " +
            "JOIN events e ON es.event_id = e.event_id " +
            "WHERE NOT (es.deleted OR u.deleted OR e.deleted) "+
            "AND es.user_id = #{userId} AND es.event_id = #{eventId}"
    )
    EventSpeaker findById(@Param("userId") int userId, @Param("eventId") int eventId);


    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers " +
            "WHERE deleted "+
            "AND user_id = #{userId} AND event_id = #{eventId}"
    )
    EventSpeaker findDeletedById(@Param("userId") int userId, @Param("eventId") int eventId);

    @Update(
            //language=PostgreSQL
            "UPDATE event_speakers SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void delete(@Param("userId") int userId, @Param("eventId") int eventId);

    @Update(
            //language=PostgreSQL
            "UPDATE event_speakers SET created_at = current_timestamp, created_by = #{createdBy} " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void update(EventSpeaker eventSpeaker);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO event_speakers(user_id, event_id, " +
            "created_by, created_at) " +
            "VALUES (#{userId}, #{eventId}, #{createdBy}, current_timestamp)"
    )
    void insert(EventSpeaker eventSpeaker);

    @Update(
            //language=PostgreSQL
            "UPDATE event_speakers SET deleted = 'false' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void resurrect(@Param("userId") int userId, @Param("eventId") int eventId);
}
