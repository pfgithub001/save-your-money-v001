package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.entity.FixedMovement;
import sbapp.save_your.money.exception.FixedMovementNotFoundException;
import sbapp.save_your.money.repository.FixedMovementRepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FixedMovementService {

    private final FixedMovementRepo fixedMovementRepo;

    @Autowired
    public FixedMovementService(FixedMovementRepo fixedMovementRepo) { this.fixedMovementRepo = fixedMovementRepo; }

    public List<FixedMovement> findAllFixedMovements() { return fixedMovementRepo.findAll(); }

    public FixedMovement findFixedMovementById(Long id) {
        return fixedMovementRepo.findFixedMovementById(id)
                .orElseThrow(()-> new FixedMovementNotFoundException("FixedMovement by id" + id + "was not found"));
    }

    public FixedMovement addFixedMovement(FixedMovement fixedMovement) { return fixedMovementRepo.save(fixedMovement); }

    public FixedMovement updateFixedMovement(FixedMovement fixedMovement) { return fixedMovementRepo.save(fixedMovement); }

    public void deleteFixedMovement(Long id) { fixedMovementRepo.deleteFixedMovementById(id); }

}
