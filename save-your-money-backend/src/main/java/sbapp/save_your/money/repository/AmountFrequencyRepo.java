package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.AmountFrequency;

public interface AmountFrequencyRepo extends JpaRepository<AmountFrequency, Long> {
    
    Optional<AmountFrequency> findAmountFrequencyById(Long id);
    void deleteAmountFrequencyById(Long id);
}
