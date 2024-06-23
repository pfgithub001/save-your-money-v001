package sbapp.save_your.money.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@MappedSuperclass
public abstract class DescriptionAmountItem extends DescriptionIntem {

    @Column(nullable = false)
    @Getter @Setter
    private BigDecimal amount;
}
