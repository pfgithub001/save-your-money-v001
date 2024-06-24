package sbapp.save_your.money.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sbapp.save_your.money.dto.FixedMovementDTO;
import sbapp.save_your.money.entity.FixedMovement;

@Mapper(uses = {AmountDateRangeMapper.class})
public interface FixedMovementMapper {
    
    FixedMovementMapper INSTANCE = Mappers.getMapper(FixedMovementMapper.class);

    @Mapping(source = "amountFrequency", target = "amountFrequencyDTO")
    @Mapping(source = "amountDateRanges", target = "amountDateRangesDTO")
    FixedMovementDTO toDto(FixedMovement fixedMovement);

    @Mapping(source = "amountFrequencyDTO", target = "amountFrequency")
    @Mapping(source = "amountDateRangesDTO", target = "amountDateRanges")
    FixedMovement toEntity(FixedMovementDTO fixedMovementDTO);
}
