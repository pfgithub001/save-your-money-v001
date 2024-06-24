package sbapp.save_your.money.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"amount_date_ranges\"")
public class AmountDateRange {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, insertable = false)
    @Getter @Setter
    private long id;

    @Column(nullable = false)
    @Getter @Setter
    private BigDecimal amount;

    @Column(nullable = false)
    @Getter @Setter
    private Date startDate;

    @Column(nullable = true)
    @Getter @Setter
    private Date endDate;

    @ManyToOne
    @JoinColumn(name="fixedMovementId")
    @Getter @Setter
    private FixedMovement fixedMovement;
    
}
