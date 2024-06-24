package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.ExpectedMovement;

public interface ExpectedMovementRepo extends JpaRepository<ExpectedMovement, Long> {
    
    Optional<ExpectedMovement> findExpectedMovementById(Long id);
    void deleteExpectedMovementById(Long id);
}
