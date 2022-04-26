package com.azzouz.apipfa.controllers;

import com.azzouz.apipfa.dto.UserDTO;
import com.azzouz.apipfa.entities.User;
import com.azzouz.apipfa.entities.enums.Category;
import com.azzouz.apipfa.repositories.UserRepository;
import com.azzouz.apipfa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
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
      @RequestParam(value = "username") final String username,
      @RequestParam(value = "email") final String email,
      @RequestParam(value = "address") final String address,
      @RequestParam(value = "preferedCategory") final Category preferedCategory,
      @RequestParam(value = "password") final String password,
      @RequestParam("file") final MultipartFile files,
      @RequestParam(value = "phoneNumber") final Long phoneNumber,
      @RequestParam(value = "locationCity") final String locationCity)
      throws IOException {
    final User userExists = userRepository.findByEmail(email);
    /* if (userExists != null) {
      bindingResult.rejectValue(
          "email", "error.userDTO", "There is already a user registered with this email ");
    }*/
    return userService.createUser(
        username, email, address, preferedCategory, password, files, phoneNumber, locationCity);
  }

  @GetMapping("/{userId}")
  public Optional<User> getProvider(@PathVariable final Long userId) {
    return userRepository.findById(userId);
  }

  @PutMapping("/update/{userId}")
  public UserDTO updateProfil(
      @PathVariable final int userId,
      @RequestParam(value = "username") final String username,
      @RequestParam(value = "email") final String email,
      @RequestParam(value = "address") final String address,
      @RequestParam(value = "preferedCategory") final Category preferedCategory,
      @RequestParam(value = "password") final String password,
      @RequestParam("file") final MultipartFile file,
      @RequestParam(value = "phoneNumber") final Long phoneNumber,
      @RequestParam(value = "locationCity") final String locationCity)
      throws IOException {
    return userService.updateProfil(
        userId,
        username,
        email,
        address,
        preferedCategory,
        password,
        file,
        phoneNumber,
        locationCity);
  }

  @PostMapping("/login")
  public UserDTO login(
      @Valid @RequestParam(value = "username") final String username,
      @RequestParam(value = "password") final String password) {
    return userService.login(username, password);
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
