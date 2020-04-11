package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.PhotoArchiveMapper;
import ru.relex.db.model.Event;
import ru.relex.db.model.PhotoArchive;
import ru.relex.services.dto.conference.PhotoArchiveDto;
import ru.relex.services.mapstruct.PhotoArchiveStruct;
import ru.relex.services.service.IPhotoArchiveService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class PhotoArchiveService implements IPhotoArchiveService {

    private PhotoArchiveMapper photoArchiveMapper;
    private PhotoArchiveStruct photoArchiveStruct;

    @Autowired
    public PhotoArchiveService(PhotoArchiveMapper photoArchiveMapper, PhotoArchiveStruct photoArchiveStruct) {
        this.photoArchiveMapper = photoArchiveMapper;
        this.photoArchiveStruct = photoArchiveStruct;
    }

    @Override
    public List<PhotoArchiveDto> getAlbumById(int conferenceId) {
        return photoArchiveStruct.toDto(photoArchiveMapper.getPhotoByIdConferences(conferenceId));
    }

    @Override
    public void deleteAlbumById(int conferenceId) {
        photoArchiveMapper.deleteAlbumById(conferenceId);
    }

    @Override
    public void deletePhotoById(int photoId) {
        photoArchiveMapper.deletePhotoById(photoId);
    }


    public PhotoArchiveDto getPhotoById(int photoId) {
        return photoArchiveStruct.toDto(photoArchiveMapper.getPhotoById(photoId));
    }
    @Override
    public PhotoArchiveDto addPhoto(@Valid PhotoArchiveDto photoArchiveDto) {
        PhotoArchive photoArchive = photoArchiveStruct.fromDto(photoArchiveDto);
        photoArchiveMapper.insert(photoArchive);
        return photoArchiveStruct.toDto(photoArchive);
    }
}
