package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.entity.ExpectedMovment;
import sbapp.save_your.money.exception.ExpectedMovmentNotFoundException;
import sbapp.save_your.money.repository.ExpectedMovmentRepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExpectedMovmentService {

    private final ExpectedMovmentRepo expectedMovmentRepo;

    @Autowired
    public ExpectedMovmentService(ExpectedMovmentRepo expectedMovmentRepo) { this.expectedMovmentRepo = expectedMovmentRepo; }

    public List<ExpectedMovment> findAllExpectedMovments() { return expectedMovmentRepo.findAll(); }

    public ExpectedMovment findExpectedMovmentById(Long id) {
        return expectedMovmentRepo.findExpectedMovmentById(id)
                .orElseThrow(()-> new ExpectedMovmentNotFoundException("ExpectedMovment by id" + id + "was not found"));
    }

    public ExpectedMovment addExpectedMovment(ExpectedMovment expectedMovment) { return expectedMovmentRepo.save(expectedMovment); }

    public ExpectedMovment updateExpectedMovment(ExpectedMovment expectedMovment) { return expectedMovmentRepo.save(expectedMovment); }

    public void deleteExpectedMovment(Long id) { expectedMovmentRepo.deleteExpectedMovmentById(id); }

}
