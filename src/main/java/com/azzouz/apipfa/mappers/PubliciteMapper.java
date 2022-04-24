package com.azzouz.apipfa.mappers;

import com.azzouz.apipfa.dto.PubliciteDTO;
import com.azzouz.apipfa.entities.Publicite;
import com.azzouz.apipfa.entities.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PubliciteMapper {
  public static PubliciteMapper MAPPER = Mappers.getMapper(PubliciteMapper.class);

  @Mapping(target = "userId", ignore = true)
  public abstract PubliciteDTO toPubliciteDTO(Publicite publicite);

  @Mapping(target = "user", ignore = true)
  public abstract Publicite toPublicite(PubliciteDTO publiciteDTO);

  @AfterMapping
  void updatePubliciteDTO(
      final Publicite publicite, @MappingTarget final PubliciteDTO publiciteDTO) {
    publiciteDTO.setLocationCity(publicite.getLocation().getCity());
    publiciteDTO.setUserId(publicite.getUser().getId());
  }

  @AfterMapping
  void updatePublicite(final PubliciteDTO publiciteDTO, @MappingTarget final Publicite publicite) {
    final User user = new User();
    user.setId(publiciteDTO.getUserId());
    publicite.setUser(user);
  }
}
