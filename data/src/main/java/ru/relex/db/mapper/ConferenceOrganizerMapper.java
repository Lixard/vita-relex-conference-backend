package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.ConferenceOrganizer;

import java.util.List;

@Mapper
public interface ConferenceOrganizerMapper {
    @Select("SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers WHERE NOT deleted ")
    List<ConferenceOrganizer> getConferenceOrganizers();

    @Select("SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers WHERE NOT deleted" +
            "WHERE user_id = #{userId}")
    ConferenceOrganizer findByUserId(@Param("userId") int userId);

    @Select("SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers WHERE NOT deleted" +
            "WHERE conference_id = #{conferenceId}")
    ConferenceOrganizer findByConferenceId(@Param("conferenceId") int conferenceId);

    @Select("SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId} AND NOT deleted ")
    ConferenceOrganizer findById(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Update("UPDATE conference_organizers SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId}")
    void delete(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Insert("INSERT INTO conference_organizers(user_id, conference_id, " +
            "created_by, created_at, deleted) " +
            "VALUES (#{userId}, #{conferenceId}, #{createdBy}, #{createdAt}, #{deleted})")
    void insert(ConferenceOrganizer conferenceOrganizer);
}
