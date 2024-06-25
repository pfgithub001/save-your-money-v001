    package sbapp.save_your.money.dto;

    import java.math.BigDecimal;

import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class ExpectedMovementDTO {

        private long id;
        private String description;
        private BigDecimal amount;
        private AmountFrequencyDTO amountFrequencyDTO;
        private Long profileId;
    }
