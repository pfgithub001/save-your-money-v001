package sbapp.save_your.money.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovementDTO {

    private long id;
    private String description;
    private BigDecimal amount;
    private Date date;
    private ExpectedMovementDTO expectedMovementDTO;

}
