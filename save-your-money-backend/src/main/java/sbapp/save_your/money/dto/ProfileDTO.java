package sbapp.save_your.money.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProfileDTO {

    private long id;
    private String profileName;
    private List<MovementDTO> movementsDTO;
    private List<ExpectedMovementDTO> expectedMovementsDTO;
    private List<FixedMovementDTO> fixedMovementsDTO;

}
