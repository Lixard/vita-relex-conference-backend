package ru.relex.services.mapstruct;

import org.mapstruct.Mapper;
import ru.relex.db.model.PhotoArchive;
import ru.relex.services.dto.conference.PhotoArchiveDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoArchiveStruct {
    PhotoArchiveDto toDto(PhotoArchive photoArchive);

    PhotoArchive fromDto(PhotoArchiveDto photoArchiveDto);

    List<PhotoArchiveDto> toDto(List<PhotoArchive> photoArchive);

    List<PhotoArchive> fromDto(List<PhotoArchiveDto> photoArchiveDto);
}
