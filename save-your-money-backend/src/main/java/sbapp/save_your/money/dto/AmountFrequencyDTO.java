    package sbapp.save_your.money.dto;

    import lombok.Getter;
    import lombok.Setter;

    @Getter
    @Setter
    public class AmountFrequencyDTO {

        private long id;
        private String frequencyName;
        private float divider;
        private float leapYearDivider;
    }
