package sbapp.save_your.money.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"fixed_movements\"")
public class FixedMovement extends DescriptionIntem {
    
    @OneToOne
    @JoinColumn(name = "amountFrequencyId", nullable = false)
    @Getter @Setter
    private AmountFrequency amountFrequency;

    @OneToMany(mappedBy = "fixedMovement")
    @Getter @Setter
    private List<AmountDateRange> amountDateRanges;

    @ManyToOne
    @JoinColumn(name="profileId")
    @Getter @Setter
    private Profile profile;

}
