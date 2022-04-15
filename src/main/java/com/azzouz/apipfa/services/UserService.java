package com.azzouz.apipfa.services;

import com.azzouz.apipfa.dto.UserDTO;
import com.azzouz.apipfa.entities.Location;
import com.azzouz.apipfa.entities.Role;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.exception.NotFoundException;
import com.azzouz.apipfa.mappers.EnableMapper;
import com.azzouz.apipfa.mappers.UserMapper;
import com.azzouz.apipfa.repositories.LocationRepository;
import com.azzouz.apipfa.repositories.RoleRepository;
import com.azzouz.apipfa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {

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
      final UserDTO userDTO, final BindingResult bindingResult, final long locationId) {
    // final Location location = locationRepository.findById(locationId);
    final User userNameExists = userRepository.findByEmail(userDTO.getUsername());

    final User emailExists = userRepository.findByEmail(userDTO.getEmail());

    if (userNameExists != null || emailExists != null) {
      bindingResult.rejectValue(
          "email or username",
          "error.user",
          "There is already a user registered with the email or with username");
    }

    final Role userRole = roleRepository.findByRole("USER");
    userDTO.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    // userDTO.setLocation(location);
    userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    userDTO.setActive(0);
    final User user = UserMapper.MAPPER.toUser(userDTO);
    final Location locationUser = locationRepository.findById(locationId);
    user.setLocation(locationUser);
    /*} else { if (locationUser == null) {
      user.setLocation(locationUser);
    }*/

    return UserMapper.MAPPER.toUserDTO(userRepository.save(user));
  }

  public User disableUserAcount(final int userId) {
    final User user =
        userRepository
            .findById(Long.valueOf(userId))
            .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + userId));
    user.setActive(0);
    return EnableMapper.MAPPER.toDisable(userRepository.save(user));
  }

  public User enableUserAcount(final int userId) {
    final User user =
        userRepository
            .findById(Long.valueOf(userId))
            .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + userId));
    user.setActive(1);
    return EnableMapper.MAPPER.toEnable(userRepository.save(user));
  }

  public UserDTO updateProfil(final long userId, final User userRequest) {
    return userRepository
        .findById(userId)
        .map(
            user -> {
              user.setUsername(userRequest.getUsername());
              user.setEmail(userRequest.getEmail());
              user.setAddress(userRequest.getAddress());
              user.setPreferedCategory(userRequest.getPreferedCategory());
              user.setPhoneNumber(userRequest.getPhoneNumber());

              return UserMapper.MAPPER.toUserDTO(userRepository.save(user));
            })
        .orElseThrow(() -> new NotFoundException("userId " + userId + " not found"));
  }
}
