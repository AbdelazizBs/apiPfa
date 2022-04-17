package com.azzouz.apipfa.services;

import com.azzouz.apipfa.dto.PubliciteDTO;
import com.azzouz.apipfa.entities.Publicite;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.entities.enums.Category;
import com.azzouz.apipfa.exception.NotFoundException;
import com.azzouz.apipfa.mappers.PubliciteMapper;
import com.azzouz.apipfa.repositories.LocationRepository;
import com.azzouz.apipfa.repositories.PubliciteRepository;
import com.azzouz.apipfa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PubliciteService {

  private final PubliciteRepository publiciteRepository;
  private final LocationRepository locationRepository;

  private final UserRepository userRepository;

  public PubliciteService(
      final PubliciteRepository publiciteRepository,
      final UserRepository userRepository,
      final LocationRepository locationRepository) {
    this.publiciteRepository = publiciteRepository;
    this.userRepository = userRepository;
    this.locationRepository = locationRepository;
  }

  public PubliciteDTO createPublicite(final PubliciteDTO publiciteDTO, final String locationCity) {
    final Publicite pub = PubliciteMapper.MAPPER.toPublicite(publiciteDTO);
    pub.setLocation(locationRepository.findByCity(locationCity));
    return PubliciteMapper.MAPPER.toPubliciteDTO(publiciteRepository.save(pub));
  }

  public List<PubliciteDTO> showAllPublicite(final Long userId) {
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1) {
      return publiciteRepository.findAll().stream()
          .map(PubliciteMapper.MAPPER::toPubliciteDTO)
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public List<PubliciteDTO> searcheWithLocation(final Long userId, final Long locationId) {
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1) {
      return publiciteRepository.findByLocationId(locationId).stream()
          .map(PubliciteMapper.MAPPER::toPubliciteDTO)
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public List<PubliciteDTO> showAllPubliciteCategory(final Long userId, final Category category) {
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1) {
      return publiciteRepository.findByCategoryAndUser_Id(category, userId).stream()
          .map(PubliciteMapper.MAPPER::toPubliciteDTO)
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public List<PubliciteDTO> showMyPublicite(final Long userId) {

    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1 || user.getActive() == 0) {
      return publiciteRepository.findByUser_Id(userId).stream()
          .map(PubliciteMapper.MAPPER::toPubliciteDTO)
          .collect(Collectors.toList());
    }
    return Collections.EMPTY_LIST;
  }

  public PubliciteDTO updatePublicite(final Long pubId, final PubliciteDTO publiciteDTO) {
    return publiciteRepository
        .findById(pubId)
        .map(
            pubb -> {
              pubb.setDescription(publiciteDTO.getDescription());
              pubb.setDate(publiciteDTO.getDate());
              pubb.setCategory(publiciteDTO.getCategory());
              return PubliciteMapper.MAPPER.toPubliciteDTO(publiciteRepository.save(pubb));
            })
        .orElseThrow(() -> new NotFoundException("Publicite Id  " + pubId + " not found"));
  }
}
