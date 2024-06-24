package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.dto.FixedMovementDTO;
import sbapp.save_your.money.entity.FixedMovement;
import sbapp.save_your.money.exception.FixedMovementNotFoundException;
import sbapp.save_your.money.repository.FixedMovementRepo;
import sbapp.save_your.money.mapper.FixedMovementMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FixedMovementService {

    private final FixedMovementRepo fixedMovementRepo;

    @Autowired
    public FixedMovementService(FixedMovementRepo fixedMovementRepo) { this.fixedMovementRepo = fixedMovementRepo; }

    public List<FixedMovementDTO> findAllFixedMovements() { 
        List<FixedMovement> fixedMovements = fixedMovementRepo.findAll();
        return fixedMovements.stream()
                .map(FixedMovementMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public FixedMovementDTO findFixedMovementById(Long id) {
        FixedMovement fixedMovement = fixedMovementRepo.findFixedMovementById(id)
                .orElseThrow(() -> new FixedMovementNotFoundException("FixedMovement by id " + id + " was not found"));
        return FixedMovementMapper.INSTANCE.toDto(fixedMovement);
    }

    public FixedMovementDTO addFixedMovement(FixedMovementDTO fixedMovementDTO) { 
        FixedMovement fixedMovement = FixedMovementMapper.INSTANCE.toEntity(fixedMovementDTO);
        FixedMovement savedFixedMovement = fixedMovementRepo.save(fixedMovement);
        return FixedMovementMapper.INSTANCE.toDto(savedFixedMovement);
    }

    public FixedMovementDTO updateFixedMovement(FixedMovementDTO fixedMovementDTO) { 
        FixedMovement fixedMovement = FixedMovementMapper.INSTANCE.toEntity(fixedMovementDTO);
        FixedMovement updatedFixedMovement = fixedMovementRepo.save(fixedMovement);
        return FixedMovementMapper.INSTANCE.toDto(updatedFixedMovement);
    }

    public void deleteFixedMovement(Long id) { 
        fixedMovementRepo.deleteFixedMovementById(id); 
    }

}
