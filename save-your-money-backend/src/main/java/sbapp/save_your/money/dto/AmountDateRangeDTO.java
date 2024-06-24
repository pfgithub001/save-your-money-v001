package sbapp.save_your.money.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmountDateRangeDTO {
    
    private long id;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;
    private long fixedMovementId;
}
