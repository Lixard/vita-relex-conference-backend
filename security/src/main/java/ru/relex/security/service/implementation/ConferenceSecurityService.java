package ru.relex.security.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.commons.model.CurrentUser;
import ru.relex.db.mapper.ConferenceMapper;
import ru.relex.db.mapper.ConferenceOrganizerMapper;
import ru.relex.db.mapper.EventMapper;
import ru.relex.services.service.IConferenceSecurityService;

@Service
public class ConferenceSecurityService implements IConferenceSecurityService {

    private final ConferenceMapper conferenceMapper;
    private final ConferenceOrganizerMapper conferenceOrganizerMapper;
    private final EventMapper eventMapper;
    private final CurrentUser currentUser;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public ConferenceSecurityService(ConferenceMapper conferenceMapper,
                                     ConferenceOrganizerMapper conferenceOrganizerMapper,
                                     EventMapper eventMapper,
                                     CurrentUser currentUser) {
        this.conferenceMapper = conferenceMapper;
        this.conferenceOrganizerMapper = conferenceOrganizerMapper;
        this.eventMapper = eventMapper;
        this.currentUser = currentUser;
    }

    @Override
    public boolean hasConferenceOwnerRights(Integer conferenceId) {
        return conferenceMapper.isOwnerRightsExists(currentUser.getId(), conferenceId);
    }

    @Override
    public boolean hasConferenceOrganizerRights(Integer conferenceId) {
        return conferenceOrganizerMapper.isOrganizerRightsExists(currentUser.getId(), conferenceId);
    }

    @Override
    public boolean hasConferenceOwnerRightsByEventId(Integer eventId) {
        int conferenceId = eventMapper.getConferenceIdByEventId(eventId);
        return conferenceMapper.isOwnerRightsExists(currentUser.getId(), conferenceId);
    }

    @Override
    public boolean hasConferenceOrganizerRightsByEventId(Integer eventId) {
        int conferenceId = eventMapper.getConferenceIdByEventId(eventId);
        return conferenceOrganizerMapper.isOrganizerRightsExists(currentUser.getId(), conferenceId);
    }
}
