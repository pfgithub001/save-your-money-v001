package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.FixedMovement;

public interface FixedMovementRepo extends JpaRepository<FixedMovement, Long> {
    
    Optional<FixedMovement> findFixedMovementById(Long id);
    void deleteFixedMovementById(Long id);
}
