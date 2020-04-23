package ru.relex.services.service;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.user.UserAnswerDto;

import javax.validation.Valid;
import java.util.List;

public interface IConferenceService {
    List<ConferenceDto> getConferences();

    UserAnswerDto getConferenceOwner(int conferenceId);

    ConferenceDto updateConferencePhoto(int conferenceId, @RequestPart("file") MultipartFile multipartFile);

    List<ConferenceDto> getConferencesWhereUserIsOwner(int userId);

    ConferenceDto findById(int conferenceId);

    ConferenceDto create(@Valid ConferenceDto conferenceDto);

    ConferenceDto update(@Valid ConferenceDto conferenceDto);

    void resurrect(int conferenceId);

    void remove(int conferenceId);
}
