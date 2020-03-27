package ru.relex.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IConferenceService;
import ru.relex.services.service.IEventService;


import java.util.List;


/**Скорее всего придеться менять урлы и контроллеры к запросам на удаление и восстановление(возможно смена метода
 * на post, delet или put)
 Пока что непонятно как это будет с фронта, пока так**/

@RestController
@RequestMapping(
        path = "/conferences",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class ConferenceController {
    private IConferenceService conferenceService;
    private IConferenceOrganizerService conferenceOrganizersService;
    private IEventService eventService;

    @Autowired
    public ConferenceController(IConferenceService conferenceService, IConferenceOrganizerService conferenceOrganizersService, IEventService eventService) {
        this.conferenceService = conferenceService;
        this.conferenceOrganizersService = conferenceOrganizersService;
        this.eventService = eventService;
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
    List<UserDto> getOrganizersByConferenceId(@PathVariable("id") int id) {
        return conferenceOrganizersService.getOrganizersByConferenceId(id);
    }

    @PutMapping("/{id}")
    ConferenceDto update(@PathVariable("id") int id, @RequestBody ConferenceDto conference) {
        conference.setConferenceId(id);
        return conferenceService.update(conference);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ConferenceDto create(@RequestBody ConferenceDto conference) {
        return conferenceService.create(conference);
    }

    @PatchMapping("/{id}/deleted")
    void remove(@PathVariable("id") int id) {
        conferenceService.remove(id);
    }

    @PatchMapping("/{id}/resurrect")
    void resurrect(@PathVariable("id") int id) {
        conferenceService.resurrect(id);
    }

    @PatchMapping(path = "/{conferenceId}/users/{userId}/delete")
    void removeOrg(@PathVariable("conferenceId") int conferenceId, @PathVariable("userId") int userId) {
        conferenceOrganizersService.remove(conferenceId,userId);
    }

    @PatchMapping(path = "/{conferenceId}/users/{userId}/resurrect")
    void resurrectOrg(@PathVariable("conferenceId") int conferenceId, @PathVariable("userId") int userId) {
        conferenceOrganizersService.resurrect(conferenceId,userId);
    }

}
