package ru.relex.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IConferenceSecurityService;
import ru.relex.services.service.IConferenceService;
import ru.relex.services.service.IEventService;

import java.util.List;

@SuppressWarnings({"SpringElInspection", "ELValidationInJSP"})
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(
        path = "/conferences",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ConferenceController {
    private IConferenceService conferenceService;
    private IConferenceOrganizerService conferenceOrganizersService;
    private IEventService eventService;
    private final IConferenceSecurityService conferenceSecurityService;

    @Autowired
    public ConferenceController(IConferenceService conferenceService,
                                IConferenceOrganizerService conferenceOrganizersService,
                                IEventService eventService,
                                IConferenceSecurityService conferenceSecurityService) {
        this.conferenceService = conferenceService;
        this.conferenceOrganizersService = conferenceOrganizersService;
        this.eventService = eventService;
        this.conferenceSecurityService = conferenceSecurityService;
    }

    @GetMapping
    List<ConferenceDto> getConferences() {
        return conferenceService.getConferences();
    }

    @GetMapping("/{id}")
    ConferenceDto findById(@PathVariable("id") int id) {
        return conferenceService.findById(id);
    }

    @GetMapping("/{id}/events")
    List<EventDto> getEventsByConferenceId(@PathVariable("id") int id) {
        return eventService.getEventsByConferenceId(id);
    }

    @GetMapping("/{id}/organizers")
    List<UserAnswerDto> getOrganizersByConferenceId(@PathVariable("id") int id) {
        return conferenceOrganizersService.getOrganizersByConferenceId(id);
    }

    @GetMapping("/{id}/owner")
    UserAnswerDto getConferenceOwner(@PathVariable("id") int conferenceId) {
        return conferenceService.getConferenceOwner(conferenceId);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || " +
            "@conferenceSecurityService.hasConferenceOrganizerRights(#id) || " +
            "@conferenceSecurityService.hasConferenceOwnerRights(#id)"
    )
    @PutMapping("/{id}")
    ConferenceDto update(@PathVariable("id") int id, @RequestBody ConferenceDto conference) {
        conference.setConferenceId(id);
        return conferenceService.update(conference);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || hasRole('ROLE_COMPANY_ACCOUNT')"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ConferenceDto create(@RequestBody ConferenceDto conference) {
        return conferenceService.create(conference);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#id)"
    )
    @DeleteMapping("/{id}/delete")
    void remove(@PathVariable("id") int id) {
        conferenceService.remove(id);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#id)"
    )
    @PatchMapping("/{id}/resurrect")
    void resurrect(@PathVariable("id") int id) {
        conferenceService.resurrect(id);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#conferenceId)"
    )
    @DeleteMapping("/{conferenceId}/organizers/{userId}/delete")
    void removeOrg(@PathVariable("conferenceId") int conferenceId, @PathVariable("userId") int userId) {
        conferenceOrganizersService.remove(conferenceId,userId);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#conferenceId)"
    )
    @PatchMapping("/{conferenceId}/organizers/{userId}/resurrect")
    void resurrectOrg(@PathVariable("conferenceId") int conferenceId, @PathVariable("userId") int userId) {
        conferenceOrganizersService.resurrect(conferenceId,userId);
    }

}
