package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.rest.service.AmazonClientService;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.speaker.EventSpeakerDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IEventSpeakerService;
import ru.relex.services.service.IEventVisitorService;
import ru.relex.services.service.IUserService;

import java.util.List;

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
    private AmazonClientService amazonClientService;
    @Autowired
    public UserController(IUserService userService, IConferenceOrganizerService conferenceOrganizerService, IEventSpeakerService eventSpeakerService, IEventVisitorService eventVisitorService,  AmazonClientService amazonClientService) {
        this.userService = userService;
        this.conferenceOrganizerService = conferenceOrganizerService;
        this.eventSpeakerService = eventSpeakerService;
        this.eventVisitorService = eventVisitorService;
        this.amazonClientService = amazonClientService;
    }

    @GetMapping()
    List<UserAnswerDto> getUsers(@RequestParam(name = "search", required = false) String search) {
        if(search == null) {
            return userService.getUsers();
        }
        return userService.getUsers(search);
    }

    @GetMapping("/{id}")
    UserAnswerDto findById(@PathVariable("id") int id) {
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
    UserAnswerDto update(@PathVariable("id") int id, @RequestParam("file") MultipartFile multipartFiles, @RequestBody UserDto user) {
        user.setUserId(id);
        amazonClientService.deleteFileFromS3Bucket(user.getLinkToImage());
        String url = amazonClientService.uploadFile(multipartFiles);
        user.setLinkToImage(url);
        return userService.update(user);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    UserAnswerDto create(@RequestParam("file") MultipartFile multipartFiles, @RequestBody UserDto user) {
        String url = amazonClientService.uploadFile(multipartFiles);
        user.setLinkToImage(url);
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

    @DeleteMapping("/{id}/delete")
    void removeUser(@PathVariable("id") int id) {
        userService.remove(id);
    }

    @PatchMapping("/{id}/resurrect")
    void resurrectUser(@PathVariable("id") int id) {
        userService.resurrect(id);
    }

    @DeleteMapping("/{userId}/schedule/{eventId}/delete")
    void removeSubscriber(@PathVariable("userId") int userId, @PathVariable("eventId") int eventId) {
        eventVisitorService.remove(userId, eventId);
    }

    @PatchMapping("/{userId}/schedule/{eventId}/resurrect")
    void resurrectSubscriber(@PathVariable("userId") int userId, @PathVariable("eventId") int eventId) {
        eventVisitorService.resurrect(userId, eventId);
    }
}
