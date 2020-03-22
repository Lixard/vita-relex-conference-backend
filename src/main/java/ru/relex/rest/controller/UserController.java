package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IEventSpeakerService;
import ru.relex.services.service.IEventVisitorService;
import ru.relex.services.service.IUserService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {
    private IUserService userService;
    private IConferenceOrganizerService conferenceOrganizerService;
    private IEventSpeakerService eventSpeakerService;
    private IEventVisitorService eventVisitorService;

    @Autowired
    public UserController(IUserService userService, IConferenceOrganizerService conferenceOrganizerService, IEventSpeakerService eventSpeakerService, IEventVisitorService eventVisitorService) {
        this.userService = userService;
        this.conferenceOrganizerService = conferenceOrganizerService;
        this.eventSpeakerService = eventSpeakerService;
        this.eventVisitorService = eventVisitorService;
    }

    @GetMapping
    List<UserDto> getUsers(@RequestParam(name = "search", required = false) String search) {
        return userService.findUsers(search);
    }

    @GetMapping("/{id}")
    UserDto findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @GetMapping("/{id}/conference")
    List<ConferenceOrganizerDto> getConferencesByUserId(@PathVariable("id") int id) {
        return conferenceOrganizerService.getConferencesByOrganizerId(id);
    }

    @GetMapping("/{id}/schedule")
    List<EventVisitorDto> getScheduleOfUser(@PathVariable("id") int id) {
        return eventVisitorService.getScheduleOfUser(id);
    }

    @GetMapping("/{id}/events")
    List<EventSpeakerDto> getEventsBySpeakerId(@PathVariable("id") int id) {
        return eventSpeakerService.getEventsBySpeakerId(id);
    }

    @PutMapping("/{id}")
    UserDto update(@PathVariable("id") int id, @RequestBody UserDto user) {
        user.setUserId(id);
        return userService.update(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }
}
