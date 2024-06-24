package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.dto.AmountFrequencyDTO;
import sbapp.save_your.money.entity.AmountFrequency;
import sbapp.save_your.money.exception.AmountFrequencyNotFoundException;
import sbapp.save_your.money.repository.AmountFrequencyRepo;
import sbapp.save_your.money.mapper.AmountFrequencyMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AmountFrequencyService {

    private final AmountFrequencyRepo amountFrequencyRepo;

    @Autowired
    public AmountFrequencyService(AmountFrequencyRepo amountFrequencyRepo) { this.amountFrequencyRepo = amountFrequencyRepo; }

    public List<AmountFrequencyDTO> findAllAmountFrequencies() { 
        List<AmountFrequency> amountFrequencies = amountFrequencyRepo.findAll();
        return amountFrequencies.stream()
                .map(AmountFrequencyMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public AmountFrequencyDTO findAmountFrequencyById(Long id) {
        AmountFrequency amountFrequency = amountFrequencyRepo.findAmountFrequencyById(id)
                .orElseThrow(() -> new AmountFrequencyNotFoundException("AmountFrequency by id " + id + " was not found"));
        return AmountFrequencyMapper.INSTANCE.toDto(amountFrequency);
    }

    public AmountFrequencyDTO addAmountFrequency(AmountFrequencyDTO amountFrequencyDTO) { 
        AmountFrequency amountFrequency = AmountFrequencyMapper.INSTANCE.toEntity(amountFrequencyDTO);
        AmountFrequency savedAmountFrequency = amountFrequencyRepo.save(amountFrequency);
        return AmountFrequencyMapper.INSTANCE.toDto(savedAmountFrequency);
    }

    public AmountFrequencyDTO updateAmountFrequency(AmountFrequencyDTO amountFrequencyDTO) { 
        AmountFrequency amountFrequency = AmountFrequencyMapper.INSTANCE.toEntity(amountFrequencyDTO);
        AmountFrequency updatedAmountFrequency = amountFrequencyRepo.save(amountFrequency);
        return AmountFrequencyMapper.INSTANCE.toDto(updatedAmountFrequency);
    }

    public void deleteAmountFrequency(Long id) { 
        amountFrequencyRepo.deleteAmountFrequencyById(id); 
    }

}
