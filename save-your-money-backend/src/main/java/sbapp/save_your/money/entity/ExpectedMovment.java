package sbapp.save_your.money.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"expected_movements\"")
public class ExpectedMovment extends DescriptionAmountItem {

    @OneToOne
    @JoinColumn(name = "amountFrequencyId", nullable = false)
    @Getter @Setter
    private AmountFrequency amountFrequency;
    
}