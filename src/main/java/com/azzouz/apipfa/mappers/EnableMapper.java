package com.azzouz.apipfa.mappers;

import com.azzouz.apipfa.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class EnableMapper {

  public static EnableMapper MAPPER = Mappers.getMapper(EnableMapper.class);

  @Mapping(target = "email", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "phoneNumber", ignore = true)
  public abstract User toEnable(User userDTO);

  @Mapping(target = "email", ignore = true)
  @Mapping(target = "address", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "phoneNumber", ignore = true)
  public abstract User toDisable(User userDTO);

  /*  @AfterMapping
  void updateUser(final UserDTO userDTO , @MappingTarget final User user) {

    user.setEmail(userDTO.get);
     final User user = new User();
      user.setId(publiciteDTO.getUserId());
      publicite.setUser(user);
  }*/
}
