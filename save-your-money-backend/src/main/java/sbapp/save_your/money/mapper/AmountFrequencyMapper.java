    package sbapp.save_your.money.mapper;

    import org.mapstruct.Mapper;
    import org.mapstruct.factory.Mappers;
    import sbapp.save_your.money.dto.AmountFrequencyDTO;
    import sbapp.save_your.money.entity.AmountFrequency;

    @Mapper
    public interface AmountFrequencyMapper {
        
        AmountFrequencyMapper INSTANCE = Mappers.getMapper(AmountFrequencyMapper.class);

        //@Mapping(source = "entityAttribute", target = "dtoAttribute")
        AmountFrequencyDTO toDto(AmountFrequency amountFrequency);

        //@Mapping(source = "dtoAttribute", target = "entityAttribute")
        AmountFrequency toEntity(AmountFrequencyDTO amountFrequencyDTO);
    }
