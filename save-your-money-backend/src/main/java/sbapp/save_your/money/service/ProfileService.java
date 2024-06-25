package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.dto.ProfileDTO;
import sbapp.save_your.money.entity.Profile;
import sbapp.save_your.money.exception.ProfileNotFoundException;
import sbapp.save_your.money.repository.ProfileRepo;
import sbapp.save_your.money.mapper.ProfileMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProfileService {

    private final ProfileRepo profileRepo;

    @Autowired
    public ProfileService(ProfileRepo profileRepo) { this.profileRepo = profileRepo; }

    public List<ProfileDTO> findAllProfiles() { 
        List<Profile> profiles = profileRepo.findAll();
        return profiles.stream()
                .map(ProfileMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public ProfileDTO findProfileById(Long id) {
        Profile profile = profileRepo.findProfileById(id)
                .orElseThrow(() -> new ProfileNotFoundException("Profile by id " + id + " was not found"));
        return ProfileMapper.INSTANCE.toDto(profile);
    }

    public ProfileDTO addProfile(ProfileDTO profileDTO) { 
        Profile profile = ProfileMapper.INSTANCE.toEntity(profileDTO);
        Profile savedProfile = profileRepo.save(profile);
        return ProfileMapper.INSTANCE.toDto(savedProfile);
    }

    public ProfileDTO updateProfile(ProfileDTO profileDTO) { 
        Profile profile = ProfileMapper.INSTANCE.toEntity(profileDTO);
        Profile updatedProfile = profileRepo.save(profile);
        return ProfileMapper.INSTANCE.toDto(updatedProfile);
    }

    public void deleteProfile(Long id) { 
        profileRepo.deleteProfileById(id); 
    }

}
