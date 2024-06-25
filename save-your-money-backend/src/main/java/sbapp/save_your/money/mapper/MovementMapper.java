package sbapp.save_your.money.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sbapp.save_your.money.dto.MovementDTO;
import sbapp.save_your.money.entity.Movement;

@Mapper(uses = {ExpectedMovementMapper.class})
public interface MovementMapper {
    
    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    @Mapping(source = "expectedMovement", target = "expectedMovementDTO")
    @Mapping(source = "profile.id", target = "profileId")
    MovementDTO toDto(Movement movement);

    @Mapping(source = "expectedMovementDTO", target = "expectedMovement")
    @Mapping(source = "profileId", target = "profile.id")
    Movement toEntity(MovementDTO movementDTO);
}
