package sbapp.save_your.money.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "\"profiles\"")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, insertable = false)
    @Getter @Setter
    private long id;

    @Column(nullable = false)
    @Getter @Setter
    private String profileName;

    @OneToMany(mappedBy = "profile")
    @Getter @Setter
    private List<Movement> movements;

    @OneToMany(mappedBy = "profile")
    @Getter @Setter
    private List<ExpectedMovement> expectedMovements;

    @OneToMany(mappedBy = "profile")
    @Getter @Setter
    private List<FixedMovement> fixedMovements;
}
