package ru.relex.db.mapper;

import org.apache.ibatis.annotations.*;
import ru.relex.db.model.Conference;
import ru.relex.db.model.PhotoArchive;


import java.util.List;

@Mapper
public interface PhotoArchiveMapper {

       @Select(
                     //language=PostgreSQL
                       "SELECT " +
                       "photo_id, " +
                       "conference_id, " +
                       "url, " +
                       "description, " +
                       "created_at " +
                       "FROM album " +
                       "WHERE conference_id = #{id}"
       )
    List<PhotoArchive> getPhotoByIdConferences(@Param("id") int id);

    @Insert(
            //language=PostgreSQL
            "INSERT INTO album(conference_id, url, description," +
                    " created_at) " +
                    "VALUES (#{conferenceId}, #{url}, #{description}, " +
                    "current_timestamp)"
    )
    @SelectKey(
            before = false,
            keyProperty = "photoId",
            keyColumn = "photo_id",
            statement = "select currval('album_photo_id_seq')",
            resultType = Integer.class
    )
    void insert(PhotoArchive photoArchive);

    @Delete(
            //language=PostgreSQL
            "DELETE FROM album WHERE photo_id = #{id}")
    void deletePhotoById(@Param("id") int id);

    @Delete(
            //language=PostgreSQL
            "DELETE FROM album WHERE conference_id = #{id}")
    void deleteAlbumById(@Param("id") int id);

    @Select(//language=PostgreSQL
                    "SELECT " +
                    "photo_id, " +
                    "conference_id, " +
                    "url, " +
                    "description, " +
                    "created_at " +
                    "FROM album " +
                    "WHERE photo_id = #{photoId}")
    PhotoArchive getPhotoById(int photoId);
}
