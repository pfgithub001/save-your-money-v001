package sbapp.save_your.money.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import sbapp.save_your.money.entity.AmountDateRange;

public interface AmountDateRangeRepo extends JpaRepository<AmountDateRange, Long> {
    
    Optional<AmountDateRange> findAmountDateRangeById(Long id);
    void deleteAmountDateRangeById(Long id);
}
