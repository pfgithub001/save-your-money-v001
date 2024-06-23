package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.entity.AmountFrequency;
import sbapp.save_your.money.exception.AmountFrequencyNotFoundException;
import sbapp.save_your.money.repository.AmountFrequencyRepo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AmountFrequencyService {

    private final AmountFrequencyRepo amountFrequencyRepo;

    @Autowired
    public AmountFrequencyService(AmountFrequencyRepo amountFrequencyRepo) { this.amountFrequencyRepo = amountFrequencyRepo; }

    public List<AmountFrequency> findAllAmountFrequencies() { return amountFrequencyRepo.findAll(); }

    public AmountFrequency findAmountFrequencyById(Long id) {
        return amountFrequencyRepo.findAmountFrequencyById(id)
                .orElseThrow(()-> new AmountFrequencyNotFoundException("AmountFrequency by id" + id + "was not found"));
    }

    public AmountFrequency addAmountFrequency(AmountFrequency amountFrequency) { return amountFrequencyRepo.save(amountFrequency); }

    public AmountFrequency updateAmountFrequency(AmountFrequency amountFrequency) { return amountFrequencyRepo.save(amountFrequency); }

    public void deleteAmountFrequency(Long id) { amountFrequencyRepo.deleteAmountFrequencyById(id); }

}
