package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.ConferenceOrganizer;

import java.util.List;

@Mapper
public interface ConferenceOrganizerMapper {
    @Select(
            //language=PostgreSQL
            "SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers WHERE NOT deleted "
    )
    List<ConferenceOrganizer> getConferenceOrganizers();

    @Select(
            //language=PostgreSQL
            "SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers WHERE NOT deleted " +
            "AND user_id = #{userId}"
    )
    List<ConferenceOrganizer> getConferencesByOrganizerId(@Param("userId") int userId);

    @Select(
            //language=PostgreSQL
            "SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers WHERE NOT deleted " +
            "AND conference_id = #{conferenceId}"
    )
    List <ConferenceOrganizer> getOrganizersByConferenceId(@Param("conferenceId") int conferenceId);

    @Select(
            //language=PostgreSQL
            "SELECT user_id, conference_id, created_by, created_at, deleted " +
            "FROM conference_organizers " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId} AND NOT deleted "
    )
    ConferenceOrganizer findById(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'true' " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId}"
    )
    void delete(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'true' " +
                    "WHERE user_id = #{userId}"
    )
    void deleteBytUserId(@Param("userId") int userId);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'true' " +
                    "WHERE conference_id = #{conferenceId}"
    )
    void deleteByConferenceId(@Param("conferenceId") int conferenceId);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO conference_organizers(user_id, conference_id, " +
            "created_by, created_at) " +
            "VALUES (#{userId}, #{conferenceId}, #{createdBy}, current_timestamp)"
    )
    void insert(ConferenceOrganizer conferenceOrganizer);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'false' " +
            "WHERE user_id = #{userId} AND conference_id = #{conferenceId}"
    )
    void resurrect(@Param("userId") int userId, @Param("conferenceId") int conferenceId);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'false' " +
                    "WHERE conference_id = #{conferenceId}"
    )
    void resurrectByConferenceId(@Param("conferenceId") int conferenceId);

    @Update(
            //language=PostgreSQL
            "UPDATE conference_organizers SET deleted = 'false' " +
                    "WHERE user_id = #{userId}"
    )
    void resurrectBytUserId(@Param("userId") int userId);
}
