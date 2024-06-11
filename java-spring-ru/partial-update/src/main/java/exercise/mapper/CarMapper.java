package exercise.mapper;

import exercise.dto.CarCreateDTO;
import exercise.dto.CarDTO;
import exercise.dto.CarUpdateDTO;
import exercise.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

// BEGIN
@Mapper(
        uses = {JsonNullableMapper.class},
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CarMapper {

    public abstract CarDTO map(Car car);

    public abstract Car map(CarCreateDTO carData);

    public abstract void update(CarUpdateDTO carData, @MappingTarget Car car);
}
// END
