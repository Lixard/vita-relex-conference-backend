package ru.relex.rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.relex.services.dto.conference.ConferenceDto;
import ru.relex.services.dto.conference.PhotoArchiveDto;
import ru.relex.services.dto.event.EventDto;
import ru.relex.services.dto.user.UserAnswerDto;
import ru.relex.services.service.*;
import ru.relex.services.service.implementation.AmazonClientService;

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
    private IPhotoArchiveService photoArchiveService;
    private AmazonClientService amazonClientService;

    @Autowired
    public ConferenceController(IConferenceService conferenceService,
                                IConferenceOrganizerService conferenceOrganizersService,
                                IEventService eventService,
                                IConferenceSecurityService conferenceSecurityService,
                                IPhotoArchiveService photoArchiveService,
                                AmazonClientService amazonClientService) {
        this.conferenceService = conferenceService;
        this.conferenceOrganizersService = conferenceOrganizersService;
        this.eventService = eventService;
        this.conferenceSecurityService = conferenceSecurityService;
        this.photoArchiveService = photoArchiveService;
        this.amazonClientService = amazonClientService;
    }

    @GetMapping
    List<ConferenceDto> getConferences() {
        return conferenceService.getConferences();
    }

    @GetMapping("/{id}")
    ConferenceDto findById(@PathVariable("id") int id) {
        return conferenceService.findById(id);
    }

    @GetMapping("/{id}/album")
    List<PhotoArchiveDto> getAlbumById(@PathVariable("id") int id) {
        return photoArchiveService.getAlbumById(id);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#id)"
    )

    @PostMapping("/{id}/album")
    PhotoArchiveDto addPhoto(@PathVariable("id") int id,
                             @RequestPart("photoArchiveDto") PhotoArchiveDto photoArchiveDto,
                             @RequestPart("file") MultipartFile multipartFile) {
        amazonClientService.uploadFile(multipartFile);
        photoArchiveDto.setUrl(amazonClientService.uploadFile(multipartFile));
        return photoArchiveService.addPhoto(photoArchiveDto);
    }

    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#id)"
    )
    @DeleteMapping("/{id}/album")
    void deleteAlbumById(@PathVariable("id") int id) {
        for (PhotoArchiveDto i:photoArchiveService.getAlbumById(id)) {
            amazonClientService.deleteFileFromS3Bucket(i.getUrl());
        }
        photoArchiveService.deleteAlbumById(id);
    }
    @PreAuthorize(
            "hasRole('ROLE_ADMIN') || @conferenceSecurityService.hasConferenceOwnerRights(#id)"
    )

    @DeleteMapping("/{id}/album/{photoId}")
    void deletePhotoById(@PathVariable("id") int id, @PathVariable("photoId") int photoId) {
        amazonClientService.deleteFileFromS3Bucket(photoArchiveService.getPhotoById(photoId).getUrl());
        photoArchiveService.deletePhotoById(photoId);
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
