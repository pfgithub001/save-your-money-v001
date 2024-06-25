package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.Profile;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    
    Optional<Profile> findProfileById(Long id);
    void deleteProfileById(Long id);
}
