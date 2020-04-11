package ru.relex.services.service;


import ru.relex.services.dto.conference.PhotoArchiveDto;

import javax.validation.Valid;
import java.util.List;

public interface IPhotoArchiveService {

    List<PhotoArchiveDto> getAlbumById(int id);

    void deleteAlbumById(int conferenceId);

    void deletePhotoById(int photoId);

    PhotoArchiveDto addPhoto(@Valid PhotoArchiveDto photoArchiveDto);

    PhotoArchiveDto getPhotoById(int photoId);
}
