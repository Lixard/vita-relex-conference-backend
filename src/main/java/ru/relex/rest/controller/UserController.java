package ru.relex.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.services.dto.organizer.ConferenceOrganizerDto;
import ru.relex.services.dto.user.UserDto;
import ru.relex.services.service.IConferenceOrganizerService;
import ru.relex.services.service.IUserService;

import java.util.List;

@RestController
@RequestMapping(
        path = "/users",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserController {
    private IUserService userService;
    private IConferenceOrganizerService conferenceOrganizersService;

    @Autowired
    public UserController(IUserService userService) {
        this.conferenceOrganizersService = conferenceOrganizersService;
        this.userService = userService;
    }

    @GetMapping
    List<UserDto> getUsers(@RequestParam(name = "search", required = false) String search) {
        return userService.findUsers(search);
    }

    @GetMapping("/{id}")
    UserDto findById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @GetMapping("/users/{id}/conference")
    ConferenceOrganizerDto findConferenceByUserId(@PathVariable("id") int id) {
        return conferenceOrganizersService.findConferenceByUserId(id);
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
