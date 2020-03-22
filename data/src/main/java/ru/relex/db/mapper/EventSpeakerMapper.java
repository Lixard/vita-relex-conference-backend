package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.ConferenceOrganizer;
import ru.relex.db.model.EventSpeaker;

import java.util.List;

@Mapper
public interface EventSpeakerMapper {
    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers WHERE NOT deleted "
    )
    List<ConferenceOrganizer> getConferenceOrganizers();

    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers WHERE NOT deleted " +
            "AND user_id = #{userId}"
    )
    ConferenceOrganizer findByUserId(@Param("userId") int userId);

    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers WHERE NOT deleted " +
            "AND event_id = #{eventId}"
    )
    ConferenceOrganizer findByConferenceId(@Param("eventId") int eventId);

    @Select(
            //language=PostgreSQL
            "SELECT user_id, event_id, created_by, created_at, deleted " +
            "FROM event_speakers " +
            "WHERE user_id = #{userId} AND event_id = #{eventId} AND NOT deleted "
    )
    ConferenceOrganizer findById(@Param("userId") int userId, @Param("eventId") int eventId);

    @Update(
            //language=PostgreSQL
            "UPDATE event_speakers SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void delete(@Param("userId") int userId, @Param("eventId") int eventId);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO event_speakers(user_id, event_id, " +
            "created_by, created_at) " +
            "VALUES (#{userId}, #{eventId}, #{createdBy}, #{createdAt})"
    )
    void insert(EventSpeaker eventSpeaker);

    @Update(
            //language=PostgreSQL
            "UPDATE event_speakers SET deleted = 'false' " +
            "WHERE user_id = #{userId} AND event_id = #{eventId}"
    )
    void resurrect(@Param("userId") int userId, @Param("eventId") int eventId);
}
