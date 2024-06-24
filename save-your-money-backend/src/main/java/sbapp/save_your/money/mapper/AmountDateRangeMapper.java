package sbapp.save_your.money.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sbapp.save_your.money.dto.AmountDateRangeDTO;
import sbapp.save_your.money.entity.AmountDateRange;

@Mapper
public interface AmountDateRangeMapper {
    
    AmountDateRangeMapper INSTANCE = Mappers.getMapper(AmountDateRangeMapper.class);

    @Mapping(source = "fixedMovement.id", target = "fixedMovementId")
    AmountDateRangeDTO toDto(AmountDateRange amountDateRange);

    @Mapping(source = "fixedMovementId", target = "fixedMovement.id")
    AmountDateRange toEntity(AmountDateRangeDTO amountDateRangeDTO);
}
