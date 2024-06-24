package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.dto.ExpectedMovementDTO;
import sbapp.save_your.money.entity.ExpectedMovement;
import sbapp.save_your.money.exception.ExpectedMovementNotFoundException;
import sbapp.save_your.money.repository.ExpectedMovementRepo;
import sbapp.save_your.money.mapper.ExpectedMovementMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExpectedMovementService {

    private final ExpectedMovementRepo expectedMovementRepo;

    @Autowired
    public ExpectedMovementService(ExpectedMovementRepo expectedMovementRepo) { this.expectedMovementRepo = expectedMovementRepo; }

    public List<ExpectedMovementDTO> findAllExpectedMovements() { 
        List<ExpectedMovement> expectedMovements = expectedMovementRepo.findAll();
        return expectedMovements.stream()
                .map(ExpectedMovementMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public ExpectedMovementDTO findExpectedMovementById(Long id) {
        ExpectedMovement expectedMovement = expectedMovementRepo.findExpectedMovementById(id)
                .orElseThrow(() -> new ExpectedMovementNotFoundException("ExpectedMovement by id " + id + " was not found"));
        return ExpectedMovementMapper.INSTANCE.toDto(expectedMovement);
    }

    public ExpectedMovementDTO addExpectedMovement(ExpectedMovementDTO expectedMovementDTO) { 
        ExpectedMovement expectedMovement = ExpectedMovementMapper.INSTANCE.toEntity(expectedMovementDTO);
        ExpectedMovement savedExpectedMovement = expectedMovementRepo.save(expectedMovement);
        return ExpectedMovementMapper.INSTANCE.toDto(savedExpectedMovement);
    }

    public ExpectedMovementDTO updateExpectedMovement(ExpectedMovementDTO expectedMovementDTO) { 
        ExpectedMovement expectedMovement = ExpectedMovementMapper.INSTANCE.toEntity(expectedMovementDTO);
        ExpectedMovement updatedExpectedMovement = expectedMovementRepo.save(expectedMovement);
        return ExpectedMovementMapper.INSTANCE.toDto(updatedExpectedMovement);
    }

    public void deleteExpectedMovement(Long id) { 
        expectedMovementRepo.deleteExpectedMovementById(id); 
    }

}
