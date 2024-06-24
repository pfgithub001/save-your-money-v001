package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.dto.MovementDTO;
import sbapp.save_your.money.entity.Movement;
import sbapp.save_your.money.exception.MovementNotFoundException;
import sbapp.save_your.money.repository.MovementRepo;
import sbapp.save_your.money.mapper.MovementMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovementService {

    private final MovementRepo movementRepo;

    @Autowired
    public MovementService(MovementRepo movementRepo) { this.movementRepo = movementRepo; }

    public List<MovementDTO> findAllMovements() { 
        List<Movement> movements = movementRepo.findAll();
        return movements.stream()
                .map(MovementMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public MovementDTO findMovementById(Long id) {
        Movement movement = movementRepo.findMovementById(id)
                .orElseThrow(() -> new MovementNotFoundException("Movement by id " + id + " was not found"));
        return MovementMapper.INSTANCE.toDto(movement);
    }

    public MovementDTO addMovement(MovementDTO movementDTO) { 
        Movement movement = MovementMapper.INSTANCE.toEntity(movementDTO);
        Movement savedMovement = movementRepo.save(movement);
        return MovementMapper.INSTANCE.toDto(savedMovement);
    }

    public MovementDTO updateMovement(MovementDTO movementDTO) { 
        Movement movement = MovementMapper.INSTANCE.toEntity(movementDTO);
        Movement updatedMovement = movementRepo.save(movement);
        return MovementMapper.INSTANCE.toDto(updatedMovement);
    }

    public void deleteMovement(Long id) { 
        movementRepo.deleteMovementById(id); 
    }

}
