package com.azzouz.apipfa.services;

import com.azzouz.apipfa.dto.UserDTO;
import com.azzouz.apipfa.entities.Location;
import com.azzouz.apipfa.entities.Picture;
import com.azzouz.apipfa.entities.Role;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.entities.enums.Category;
import com.azzouz.apipfa.exception.NotFoundException;
import com.azzouz.apipfa.mappers.EnableMapper;
import com.azzouz.apipfa.mappers.UserMapper;
import com.azzouz.apipfa.repositories.LocationRepository;
import com.azzouz.apipfa.repositories.PictureRepository;
import com.azzouz.apipfa.repositories.RoleRepository;
import com.azzouz.apipfa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
  @Autowired private PictureRepository pictureRepository;
  @Autowired private RoleRepository roleRepository;
  @Autowired private LocationRepository locationRepository;
  private Boolean userProfilb;
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  // private final ConfirmationTokenService confirmationTokenService;

  @Autowired
  public UserService(
      final UserRepository userRepository, final BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  /*  @Autowired
      public UserService(final UserRepository userRepository,
                         final BCryptPasswordEncoder
                                 bCryptPasswordEncoder) {
          this.userRepository = userRepository;
          this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      }
  */

  public UserDTO login(final String username, final String password) {
    // final String encodedPassword = bCryptPasswordEncoder.encode(password);
    final User userDb = userRepository.findByUsername(username);
    if (bCryptPasswordEncoder.matches(password, userDb.getPassword())) {
      return UserMapper.MAPPER.toUserDTO(userDb);
    }
    throw new NotFoundException("cannot found user");
  }

  public UserDTO createUser(
      final String username,
      final String email,
      final String address,
      final Category preferedCategory,
      final String password,
      final MultipartFile files,
      final Long phoneNumber,
      final String locationCity)
      throws IOException {
    // final Location location = locationRepository.findById(locationId);
    final StringBuilder fileName = new StringBuilder();
    final String fileNamee = StringUtils.cleanPath(files.getOriginalFilename());
    final Picture fileDBB;
    final User userNameExists = userRepository.findByEmail(username);
    final User emailExists = userRepository.findByEmail(email);
    if (userNameExists != null || emailExists != null) {
      System.out.println("There is already a user registered with the email or with username");
    }
    fileDBB = new Picture(fileNamee, files.getBytes());
    pictureRepository.save(fileDBB);
    final Role userRole = roleRepository.findByRole("USER");
    final UserDTO userDTO = new UserDTO();
    userDTO.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    // userDTO.setLocation(location);
    userDTO.setPassword(bCryptPasswordEncoder.encode(password));
    userDTO.setUsername(username);
    userDTO.setAddress(address);
    userDTO.setPicture(fileDBB);
    userDTO.setEmail(email);
    userDTO.setPhoneNumber(phoneNumber);
    userDTO.setPreferedCategory(preferedCategory);
    userDTO.setActive(0);
    final String userCity = String.valueOf(locationRepository.findByCity(locationCity));
    final User user = UserMapper.MAPPER.toUser(userDTO);
    user.setLocation(locationRepository.findByCity(locationCity));
    return UserMapper.MAPPER.toUserDTO(userRepository.save(user));

    // final Location locationUser = locationRepository.findByCity(locationCity);
    // user.setLocation(locationUser);
    /*} else { if (locationUser == null) {
      user.setLocation(locationUser);
    }*/

  }

  public UserDTO updateProfil(
      final long userId,
      final String username,
      final String email,
      final String address,
      final Category preferedCategory,
      final String password,
      final MultipartFile file,
      final Long phoneNumber,
      final String locationCity)
      throws IOException {
    final Location location = locationRepository.findByCity(locationCity);
    final StringBuilder fileName = new StringBuilder();
    final String fileNamee = StringUtils.cleanPath(file.getOriginalFilename());
    final Picture fileDBB;
    fileDBB = new Picture(fileNamee, file.getBytes());
    pictureRepository.save(fileDBB);
    return userRepository
        .findById(userId)
        .map(
            user -> {
              user.setUsername(username);
              user.setEmail(email);
              user.setAddress(address);
              user.setPreferedCategory(preferedCategory);
              user.setPhoneNumber(phoneNumber);
              user.setPicture(fileDBB);
              user.setPassword(password);
              user.setLocation(location);

              return UserMapper.MAPPER.toUserDTO(userRepository.save(user));
            })
        .orElseThrow(() -> new NotFoundException("userId " + userId + " not found"));
  }

  public User disableUserAcount(final int userId) {
    final User user =
        userRepository
            .findById(Long.valueOf(userId))
            .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + userId));
    user.setActive(0);
    user.setLocation(user.getLocation());
    return EnableMapper.MAPPER.toDisable(userRepository.save(user));
  }

  public User enableUserAcount(final int userId) {
    final User user =
        userRepository
            .findById(Long.valueOf(userId))
            .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + userId));
    user.setActive(1);
    user.setLocation(user.getLocation());
    return EnableMapper.MAPPER.toEnable(userRepository.save(user));
  }
}
