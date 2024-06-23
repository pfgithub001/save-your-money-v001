package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.Movement;

public interface MovementRepo extends JpaRepository<Movement, Long> {
    
    Optional<Movement> findMovementById(Long id);
    void deleteMovementById(Long id);
}
