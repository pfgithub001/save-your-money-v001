package sbapp.save_your.money.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sbapp.save_your.money.dto.ProfileDTO;
import sbapp.save_your.money.entity.Profile;

@Mapper(uses = {MovementMapper.class, ExpectedMovementMapper.class, FixedMovementMapper.class})
public interface ProfileMapper {
    
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    @Mapping(source = "movements", target = "movementsDTO")
    @Mapping(source = "expectedMovements", target = "expectedMovementsDTO")
    @Mapping(source = "fixedMovements", target = "fixedMovementsDTO")
    ProfileDTO toDto(Profile profile);

    @Mapping(source = "movementsDTO", target = "movements")
    @Mapping(source = "expectedMovementsDTO", target = "expectedMovements")
    @Mapping(source = "fixedMovementsDTO", target = "fixedMovements")
    Profile toEntity(ProfileDTO profileDTO);
}
