package ru.relex.services.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.db.mapper.ConferenceMapper;
import ru.relex.db.model.Conference;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.mapstruct.ConferenceStruct;
import ru.relex.services.service.IConferenceService;

import java.util.List;

@Service
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
        List<ConferenceDto> conferences = conferenceStruct.toDto(conferenceMapper.getConferences());
        return conferences;
    }

    @Override
    public ConferenceDto findById(int conferenceId) {
        ConferenceDto conference = conferenceStruct.toDto(conferenceMapper.findById(conferenceId));
        return conference;
    }

    @Override
    public ConferenceDto create(ConferenceDto conferenceDto) {
        Conference conference = conferenceStruct.fromDto(conferenceDto);
        conferenceMapper.insert(conference);
        return conferenceStruct.toDto(conference);
    }

    @Override
    public ConferenceDto update(ConferenceDto conferenceDto) {
        Conference conference = conferenceStruct.fromDto(conferenceDto);
        conferenceMapper.update(conference);
        return conferenceStruct.toDto(conference);
    }

    @Override
    public void remove(int conferenceId) {
        conferenceMapper.delete(conferenceId);
    }


}
