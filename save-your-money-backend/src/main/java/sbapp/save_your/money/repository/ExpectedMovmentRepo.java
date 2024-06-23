package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.ExpectedMovment;

public interface ExpectedMovmentRepo extends JpaRepository<ExpectedMovment, Long> {
    
    Optional<ExpectedMovment> findExpectedMovmentById(Long id);
    void deleteExpectedMovmentById(Long id);
}
