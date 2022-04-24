package com.azzouz.apipfa.services;

import com.azzouz.apipfa.dto.PubliciteDTO;
import com.azzouz.apipfa.entities.Location;
import com.azzouz.apipfa.entities.Picture;
import com.azzouz.apipfa.entities.Publicite;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.entities.enums.Category;
import com.azzouz.apipfa.exception.NotFoundException;
import com.azzouz.apipfa.mappers.PubliciteMapper;
import com.azzouz.apipfa.repositories.LocationRepository;
import com.azzouz.apipfa.repositories.PictureRepository;
import com.azzouz.apipfa.repositories.PubliciteRepository;
import com.azzouz.apipfa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PubliciteService {
  @Autowired private PictureRepository pictureRepository;
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

  public List<PubliciteDTO> showAllPublicite(final Long userId) {
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1) {
      // reverseList = new ArrayList<>();
      final List<PubliciteDTO> pubR =
          publiciteRepository.findPubliciteShared(userId).stream()
              .map(publicite -> PubliciteMapper.MAPPER.toPubliciteDTO(publicite))
              .collect(Collectors.toList());
      // reverseList= Collections.singletonList(Collections.reverse(pubR));
      // for (int i = pubR.size() - 1; i >= 0; i--) {
      // get current element
      // final PubliciteDTO element = pubR.get(i);
      // add it to the new list
      // reverseList.add(element);
      Collections.reverse(pubR);
      return pubR;
    }

    return Collections.EMPTY_LIST;
  }

  public List<PubliciteDTO> searcheWithLocation(final Long userId, final String locationCity) {
    final List reverseList = new ArrayList<>();
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1) {
      final List<PubliciteDTO> pubR =
          publiciteRepository.findByLocation_City(locationCity).stream()
              .map(PubliciteMapper.MAPPER::toPubliciteDTO)
              .collect(Collectors.toList());
      Collections.reverse(pubR);
      return pubR;
    }
    return Collections.EMPTY_LIST;
  }

  public List<PubliciteDTO> showAllPubliciteCategory(final Long userId, final Category category) {
    final List reverseList = new ArrayList<>();
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1) {
      final List<PubliciteDTO> pubR =
          publiciteRepository.findByCategoryAndUser_Id(category, userId).stream()
              .map(PubliciteMapper.MAPPER::toPubliciteDTO)
              .collect(Collectors.toList());
      Collections.reverse(pubR);
      return pubR;
    }
    return Collections.EMPTY_LIST;
  }

  public List<PubliciteDTO> showMyPublicite(final Long userId) {
    final List reverseList = new ArrayList<>();
    final User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException("cannot found user"));
    if (user.getActive() == 1 || user.getActive() == 0) {
      final List<PubliciteDTO> pubR =
          publiciteRepository.findByUser_Id(userId).stream()
              .map(PubliciteMapper.MAPPER::toPubliciteDTO)
              .collect(Collectors.toList());
      Collections.reverse(pubR);
      return pubR;
    }
    return Collections.EMPTY_LIST;
  }

  public PubliciteDTO updatePublicite(
      final Long pubId,
      final String description,
      final Long userId,
      final Category category,
      final MultipartFile fileN,
      final String locationCity)
      throws IOException {
    final String fileNamee = StringUtils.cleanPath(fileN.getOriginalFilename());
    final Picture fileDBB = new Picture(fileNamee, fileN.getBytes());
    pictureRepository.save(fileDBB);
    final Location location = locationRepository.findByCity(locationCity);
    final PubliciteDTO publiciteDTO = new PubliciteDTO();
    publiciteDTO.setUserId(userId);
    publiciteDTO.setCategory(category);
    publiciteDTO.setDescription(description);
    publiciteDTO.setPicture(Collections.singletonList(fileDBB));

    return publiciteRepository
        .findById(pubId)
        .map(
            pubb -> {
              pubb.setDescription(publiciteDTO.getDescription());
              pubb.setCategory(publiciteDTO.getCategory());
              pubb.setLocation(location);
              pubb.setPicture(new ArrayList<Picture>(publiciteDTO.getPicture()));
              return PubliciteMapper.MAPPER.toPubliciteDTO(publiciteRepository.save(pubb));
            })
        .orElseThrow(() -> new NotFoundException("Publicite Id  " + pubId + " not found"));
  }

  public PubliciteDTO createPublicite(
      final String description,
      final Long userId,
      final Category category,
      final MultipartFile fileN,
      final String locationCity)
      throws IOException {
    final String fileName = StringUtils.cleanPath(fileN.getOriginalFilename());
    final Picture fileDB = new Picture(fileName, fileN.getBytes());
    pictureRepository.save(fileDB);
    final PubliciteDTO publiciteDTO = new PubliciteDTO();
    publiciteDTO.setUserId(userId);
    publiciteDTO.setCategory(category);
    publiciteDTO.setDescription(description);
    publiciteDTO.setPicture(Collections.singletonList(fileDB));
    final Publicite pub = PubliciteMapper.MAPPER.toPublicite(publiciteDTO);
    pub.setLocation(locationRepository.findByCity(locationCity));
    return PubliciteMapper.MAPPER.toPubliciteDTO(publiciteRepository.save(pub));
  }
}
