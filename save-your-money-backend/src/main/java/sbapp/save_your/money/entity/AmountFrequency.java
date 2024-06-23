package sbapp.save_your.money.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"amount_frequencies\"")
public class AmountFrequency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, insertable = false)
    @Getter @Setter
    private long id;

    @Column(nullable = false)
    @Getter @Setter
    private String frequencyName;

    @Column(nullable = false)
    @Getter @Setter
    private float divider;

    @Column(nullable = false)
    @Getter @Setter
    private float leapYearDivider;
}
