package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.shedule.EventVisitorDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IEventSpeakerService;
import ru.relex.services.service.IEventVisitorService;
import ru.relex.services.service.IUserService;

import java.util.List;

/**Скорее всего придеться менять урлы и контроллеры к запросам на удаление и восстановление(возможно смена метода
 * на post, delet или put)
Пока что непонятно как это будет с фронта, пока так**/
@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}/conferences")
    List<ConferenceDto> getConferencesByUserId(@PathVariable("id") int id) {
        return conferenceOrganizerService.getConferencesByOrganizerId(id);
    }

    @GetMapping("/{id}/schedule")
    List<EventDto> getScheduleOfUser(@PathVariable("id") int id) {
        return eventVisitorService.getScheduleOfUser(id);
    }

    @GetMapping("/{id}/events")
    List<EventDto> getEventsBySpeakerId(@PathVariable("id") int id) {
        return eventSpeakerService.getEventsBySpeakerId(id);
    }

    @PutMapping("/{id}")
    UserDto update(@PathVariable("id") int id, @RequestBody UserDto user) {
        user.setUserId(id);
        return userService.update(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDto create(@RequestBody UserDto user) {
        return userService.create(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/assign/conference",consumes = MediaType.APPLICATION_JSON_VALUE)
    void assignToConference(@RequestBody ConferenceOrganizerDto organizer) {
        conferenceOrganizerService.assignToConference(organizer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/assign/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    void assignToEvent(@RequestBody EventSpeakerDto speaker) {
        eventSpeakerService.assignToEvent(speaker);
    }

    @PatchMapping("/{id}/deleted")
    void removeUser(@PathVariable("id") int id) {
        userService.remove(id);
    }

    @PatchMapping("/{id}/resurrect")
    void resurrectUser(@PathVariable("id") int id) {
        userService.resurrect(id);
    }

    @PatchMapping(path = "/{userId}/events{eventId}/delete")
    void removeSubscriber(@PathVariable("eventId") int eventId, @PathVariable("userId") int userId) {
        eventVisitorService.remove(eventId,userId);
    }

    @PatchMapping(path = "/{userId}/events{eventId}/resurrect")
    void resurrectSubscriber(@PathVariable("eventId") int eventId, @PathVariable("userId") int userId) {
        eventVisitorService.resurrect(eventId,userId);
    }
}
