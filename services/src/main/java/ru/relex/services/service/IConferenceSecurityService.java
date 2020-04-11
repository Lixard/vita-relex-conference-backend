package ru.relex.services.service;

public interface IConferenceSecurityService {
    boolean hasConferenceOwnerRights(Integer conferenceId);
    boolean hasConferenceOrganizerRights(Integer conferenceId);
    boolean hasConferenceOwnerRightsByEventId(Integer eventId);
    boolean hasConferenceOrganizerRightsByEventId(Integer eventId);
}
