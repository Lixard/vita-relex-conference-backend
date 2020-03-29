package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.relex.db.mapper.ConferenceMapper;
import ru.relex.db.model.Conference;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.mapstruct.ConferenceStruct;
import ru.relex.services.service.IConferenceService;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class ConferenceService implements IConferenceService {
    private ConferenceMapper conferenceMapper;
    private ConferenceStruct conferenceStruct;

    @Autowired
    public ConferenceService(ConferenceMapper conferenceMapper, ConferenceStruct conferenceStruct) {
        this.conferenceMapper = conferenceMapper;
        this.conferenceStruct = conferenceStruct;
    }

    @Override
    public List<ConferenceDto> getConferences() {
        return conferenceStruct.toDto(conferenceMapper.getConferences());
    }

    @Override
    public ConferenceDto findById(int conferenceId) {
        return conferenceStruct.toDto(conferenceMapper.findById(conferenceId));
    }

    @Override
    public ConferenceDto create(@Valid ConferenceDto conferenceDto) {
        Conference conference = conferenceStruct.fromDto(conferenceDto);
        conferenceMapper.insert(conference);
        return conferenceStruct.toDto(conference);
    }

    @Override
    public ConferenceDto update(@Valid ConferenceDto conferenceDto) {
        Conference conference = conferenceStruct.fromDto(conferenceDto);
        conferenceMapper.update(conference);
        return conferenceStruct.toDto(conference);
    }

    @Override
    public void remove(int conferenceId) {
        conferenceMapper.delete(conferenceId);
    }

    @Override
    public void resurrect(int conferenceId) {
        conferenceMapper.resurrect(conferenceId);
    }
}
