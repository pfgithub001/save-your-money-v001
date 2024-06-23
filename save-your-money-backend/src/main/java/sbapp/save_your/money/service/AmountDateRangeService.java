package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.entity.AmountDateRange;
import sbapp.save_your.money.exception.AmountDateRangeNotFoundException;
import sbapp.save_your.money.repository.AmountDateRangeRepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AmountDateRangeService {

    private final AmountDateRangeRepo amountDateRangeRepo;

    @Autowired
    public AmountDateRangeService(AmountDateRangeRepo amountDateRangeRepo) { this.amountDateRangeRepo = amountDateRangeRepo; }

    public List<AmountDateRange> findAllAmountDateRanges() { return amountDateRangeRepo.findAll(); }

    public AmountDateRange findAmountDateRangeById(Long id) {
        return amountDateRangeRepo.findAmountDateRangeById(id)
                .orElseThrow(()-> new AmountDateRangeNotFoundException("AmountDateRange by id" + id + "was not found"));
    }

    public AmountDateRange addAmountDateRange(AmountDateRange amountDateRange) { return amountDateRangeRepo.save(amountDateRange); }

    public AmountDateRange updateAmountDateRange(AmountDateRange amountDateRange) { return amountDateRangeRepo.save(amountDateRange); }

    public void deleteAmountDateRange(Long id) { amountDateRangeRepo.deleteAmountDateRangeById(id); }

}
