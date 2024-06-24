package sbapp.save_your.money.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"movements\"")
public class Movement extends DescriptionAmountItem {

    @Column(nullable = false)
    @Getter @Setter
    private Date date;

    @ManyToOne
    @JoinColumn(name="expectedMovementId")
    @Getter @Setter
    private ExpectedMovement expectedMovement;
    
}
