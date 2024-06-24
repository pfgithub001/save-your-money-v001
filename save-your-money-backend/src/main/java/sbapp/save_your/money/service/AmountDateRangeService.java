package sbapp.save_your.money.service;

import jakarta.transaction.Transactional;
import sbapp.save_your.money.dto.AmountDateRangeDTO;
import sbapp.save_your.money.entity.AmountDateRange;
import sbapp.save_your.money.exception.AmountDateRangeNotFoundException;
import sbapp.save_your.money.repository.AmountDateRangeRepo;
import sbapp.save_your.money.mapper.AmountDateRangeMapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AmountDateRangeService {

    private final AmountDateRangeRepo amountDateRangeRepo;

    @Autowired
    public AmountDateRangeService(AmountDateRangeRepo amountDateRangeRepo) { this.amountDateRangeRepo = amountDateRangeRepo; }

    public List<AmountDateRangeDTO> findAllAmountDateRanges() { 
        List<AmountDateRange> amountDateRanges = amountDateRangeRepo.findAll();
        return amountDateRanges.stream()
                .map(AmountDateRangeMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    public AmountDateRangeDTO findAmountDateRangeById(Long id) {
        AmountDateRange amountDateRange = amountDateRangeRepo.findAmountDateRangeById(id)
                .orElseThrow(() -> new AmountDateRangeNotFoundException("AmountDateRange by id " + id + " was not found"));
        return AmountDateRangeMapper.INSTANCE.toDto(amountDateRange);
    }

    public AmountDateRangeDTO addAmountDateRange(AmountDateRangeDTO amountDateRangeDTO) { 
        AmountDateRange amountDateRange = AmountDateRangeMapper.INSTANCE.toEntity(amountDateRangeDTO);
        AmountDateRange savedAmountDateRange = amountDateRangeRepo.save(amountDateRange);
        return AmountDateRangeMapper.INSTANCE.toDto(savedAmountDateRange);
    }

    public AmountDateRangeDTO updateAmountDateRange(AmountDateRangeDTO amountDateRangeDTO) { 
        AmountDateRange amountDateRange = AmountDateRangeMapper.INSTANCE.toEntity(amountDateRangeDTO);
        AmountDateRange updatedAmountDateRange = amountDateRangeRepo.save(amountDateRange);
        return AmountDateRangeMapper.INSTANCE.toDto(updatedAmountDateRange);
    }

    public void deleteAmountDateRange(Long id) { 
        amountDateRangeRepo.deleteAmountDateRangeById(id); 
    }

}
