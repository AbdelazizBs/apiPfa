package com.azzouz.apipfa.mappers;

import com.azzouz.apipfa.dto.UserDTO;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.repositories.LocationRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

  @Autowired private LocationRepository locationRepository;
  public static UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  @Mapping(target = "password", ignore = true)
  @Mapping(target = "locationId", ignore = true)
  public abstract UserDTO toUserDTO(User user);

  public abstract User toUser(UserDTO userDTO);

  @AfterMapping
  void updateUserDTO(final User user, @MappingTarget final UserDTO userDTO) {
    userDTO.setLocationId(user.getLocation().getId());
  }
}
