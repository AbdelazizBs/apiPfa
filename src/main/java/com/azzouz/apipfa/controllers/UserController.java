package com.azzouz.apipfa.controllers;

import com.azzouz.apipfa.dto.UserDTO;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.repositories.UserRepository;
import com.azzouz.apipfa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

  boolean access = true;

  @Autowired private UserRepository userRepository;

  @Autowired private UserService userService;

  @PostMapping("/registre")
  UserDTO createUser(
      @Valid @RequestBody final UserDTO userDTO,
      final BindingResult bindingResult,
      @RequestParam(value = "locationId") final Long locationId) {
    final User userExists = userRepository.findByEmail(userDTO.getEmail());
    if (userExists != null) {
      bindingResult.rejectValue(
          "email", "error.userDTO", "There is already a user registered with this email ");
    }
    return userService.createUser(userDTO, bindingResult, locationId);
  }

  @GetMapping("/{userId}")
  public Optional<User> getProvider(@PathVariable final Long userId) {
    return userRepository.findById(userId);
  }

  @PostMapping("/login")
  public UserDTO login(
      @Valid @RequestParam(value = "username") final String username,
      @RequestParam(value = "password") final String password) {
    return userService.login(username, password);
  }

  @PutMapping("/update/{userId}")
  public UserDTO updateProfil(
      @PathVariable final int userId, @Valid @RequestBody final User userRequest) {
    return userService.updateProfil(userId, userRequest);
  }
  /* @PostMapping("/login")
      UserDTO loginUser(@Valid @RequestBody final UserDTO usen, final BindingResult bindingResult) {
          return userService.loginUser(userDTO, bindingResult);
      }

  */

  @GetMapping("/{userId}/enable")
  User enableUserAcount(@PathVariable("userId") final int userId) {
    return userService.enableUserAcount(userId);
  }

  @GetMapping("/{userId}/disable")
  User disableUserAcount(@PathVariable("userId") final int userId) {
    return userService.disableUserAcount(userId);
  }
}
