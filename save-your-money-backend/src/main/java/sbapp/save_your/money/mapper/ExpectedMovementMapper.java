    package sbapp.save_your.money.mapper;

    import org.mapstruct.Mapper;
    import org.mapstruct.Mapping;
    import org.mapstruct.factory.Mappers;
    import sbapp.save_your.money.dto.ExpectedMovementDTO;
    import sbapp.save_your.money.entity.ExpectedMovement;

    @Mapper
    public interface ExpectedMovementMapper {
        
        ExpectedMovementMapper INSTANCE = Mappers.getMapper(ExpectedMovementMapper.class);

        @Mapping(source = "amountFrequency", target = "amountFrequencyDTO")
        @Mapping(source = "profile.id", target = "profileId")
        ExpectedMovementDTO toDto(ExpectedMovement expectedMovement);

        @Mapping(source = "amountFrequencyDTO", target = "amountFrequency")
        @Mapping(source = "profileId", target = "profile.id")
        ExpectedMovement toEntity(ExpectedMovementDTO expectedMovementDTO);
    }
