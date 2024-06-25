package sbapp.save_your.money.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sbapp.save_your.money.dto.ProfileDTO;
import sbapp.save_your.money.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) { 
        this.profileService = profileService; 
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        List<ProfileDTO> profilesDTO = profileService.findAllProfiles();
        return new ResponseEntity<>(profilesDTO, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable("id") Long id) {
        ProfileDTO profileDTO = profileService.findProfileById(id);
        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }       

    @PostMapping("/add")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO newProfileDTO = profileService.addProfile(profileDTO);
        return new ResponseEntity<>(newProfileDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) {
        ProfileDTO updatedProfileDTO = profileService.updateProfile(profileDTO);
        return new ResponseEntity<>(updatedProfileDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Long id) {
        profileService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

