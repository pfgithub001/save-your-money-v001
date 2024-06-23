package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.entity.Movement;
import sbapp.save_your.money.exception.MovementNotFoundException;
import sbapp.save_your.money.repository.MovementRepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovementService {

    private final MovementRepo movementRepo;

    @Autowired
    public MovementService(MovementRepo movementRepo) { this.movementRepo = movementRepo; }

    public List<Movement> findAllMovements() { return movementRepo.findAll(); }

    public Movement findMovementById(Long id) {
        return movementRepo.findMovementById(id)
                .orElseThrow(()-> new MovementNotFoundException("Movement by id" + id + "was not found"));
    }

    public Movement addMovement(Movement movement) { return movementRepo.save(movement); }

    public Movement updateMovement(Movement movement) { return movementRepo.save(movement); }

    public void deleteMovement(Long id) { movementRepo.deleteMovementById(id); }

}
