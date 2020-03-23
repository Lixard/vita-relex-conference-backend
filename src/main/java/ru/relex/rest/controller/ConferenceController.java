package ru.relex.rest.controller;

import javafx.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IConferenceService;
import ru.relex.services.service.IEventService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/conferences",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ConferenceController {
    private IConferenceService conferenceService;
    private IConferenceOrganizerService conferenceOrganizersService;
    private IEventService eventService;

    public ConferenceController(IConferenceService conferenceService, IConferenceOrganizerService conferenceOrganizersService, IEventService eventService) {
        this.conferenceService = conferenceService;
        this.conferenceOrganizersService = conferenceOrganizersService;
        this.eventService = eventService;
    }

    @Autowired


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
    List<ConferenceOrganizerDto> getOrganizersByConferenceId(@PathVariable("id") int id) {
        return conferenceOrganizersService.getOrganizersByConferenceId(id);
    }

    @PutMapping("/{id}")
    ConferenceDto update(@PathVariable("id") int id, @RequestBody ConferenceDto conference) {
        conference.setConferenceId(id);
        return conferenceService.update(conference);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ConferenceDto create(@RequestBody ConferenceDto conference) {
        return conferenceService.create(conference);
    }
}
