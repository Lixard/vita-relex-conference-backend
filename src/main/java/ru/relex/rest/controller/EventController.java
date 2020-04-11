package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.service.*;

import java.util.List;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(
        path = "/events",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class EventController {
    private IEventService eventService;
    private IEventSpeakerService eventSpeakerService;
    private IEventVisitorService eventVisitorService;
    private final IConferenceSecurityService conferenceSecurityService;
    private final IUserSecurityService userSecurityService;

    @Autowired
    public EventController(IEventService eventService,
                           IEventSpeakerService eventSpeakerService,
                           IEventVisitorService eventVisitorService,
                           IConferenceSecurityService conferenceSecurityService,
                           IUserSecurityService userSecurityService) {
        this.eventService = eventService;
        this.eventSpeakerService =  eventSpeakerService;
        this.eventVisitorService = eventVisitorService;
        this.conferenceSecurityService = conferenceSecurityService;
        this.userSecurityService = userSecurityService;
    }

    @GetMapping
    List<EventDto> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{id}")
    EventDto findById(@PathVariable("id") int id) {
        return eventService.findById(id);
    }

    @GetMapping("/{id}/speakers")
    List<UserAnswerDto> getSpeakerByEventId(@PathVariable("id") int id) {
        return eventSpeakerService.getSpeakersByEventId(id);
    }

    @GetMapping("/{id}/users")
    List<UserAnswerDto> getVisitorsByEventId(@PathVariable("id") int id) {
        return eventVisitorService.getVisitorsByEventId(id);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOwnerRightsByEventId(#id) || " +
            "@conferenceSecurityService.hasConferenceOrganizerRightsByEventId(#id)"
    )
    @PutMapping("/{id}")
    EventDto update(@PathVariable("id") int id, @RequestBody EventDto event) {
        event.setEventId(id);
        return eventService.update(event);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOrganizerRights(#event.getConferenceId()) || " +
            "@conferenceSecurityService.hasConferenceOwnerRights(#event.getConferenceId())"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    EventDto create(@RequestBody EventDto event) {
        return eventService.create(event);
    }

    @PreAuthorize(
            "@userSecurityService.isTheSameUser(#visitor.getUserId())"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/subscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    void subscribeOnEvent(@RequestBody EventVisitorDto visitor) {
        eventVisitorService.subscribeOnEvent(visitor);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOrganizerRightsByEventId(#id) || " +
            "@conferenceSecurityService.hasConferenceOwnerRightsByEventId(#id)"
    )
    @DeleteMapping("/{id}/delete")
    void remove(@PathVariable("id") int id) {
        eventService.remove(id);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOrganizerRightsByEventId(#id) || " +
            "@conferenceSecurityService.hasConferenceOwnerRightsByEventId(#id)"
    )
    @PatchMapping("/{id}/resurrect")
    void resurrect(@PathVariable("id") int id) {
        eventService.resurrect(id);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOrganizerRightsByEventId(#eventId) || " +
            "@conferenceSecurityService.hasConferenceOwnerRightsByEventId(#eventId)"
    )
    @DeleteMapping("/{eventId}/speakers/{userId}/delete")
    void removeSpeaker(@PathVariable("eventId") int eventId, @PathVariable("userId") int userId) {
        eventSpeakerService.remove(userId, eventId);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOrganizerRightsByEventId(#eventId) || " +
            "@conferenceSecurityService.hasConferenceOwnerRightsByEventId(#eventId)"
    )
    @PatchMapping("/{eventId}/speakers/{userId}/resurrect")
    void resurrectSpeaker(@PathVariable("eventId") int eventId, @PathVariable("userId") int userId) {
        eventSpeakerService.resurrect(userId, eventId);
    }

}
