package sbapp.save_your.money.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FixedMovementDTO {

    private long id;
    private String description;
    private AmountFrequencyDTO amountFrequencyDTO;
    private List<AmountDateRangeDTO> amountDateRangesDTO;
}
