package com.azzouz.apipfa.controllers;

import com.azzouz.apipfa.dto.PubliciteDTO;
import com.azzouz.apipfa.entities.enums.Category;
import com.azzouz.apipfa.services.PubliciteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/annances")
@CrossOrigin(origins = "*")
public class PubliciteController {

  public static String uploadDirectory =
      System.getProperty("user.dir") + "/src/main/resources/static/uploads";

  @Autowired private PubliciteService publiciteService;
  //  public static String uploadDirectory = System.getProperty("user.dir") +
  // "/src/main/resources/static/uploads";

  @PostMapping("/add")
  PubliciteDTO createPublicite(
      @Valid @RequestBody final PubliciteDTO publiciteDTO,
      final BindingResult result,
      @RequestParam(value = "locationId") final Long locationId
      /*,@RequestParam("picture") final MultipartFile[] files*/ ) {

    /*final StringBuilder fileName = new StringBuilder();
    final MultipartFile file = files[0];
    final Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

    fileName.append(file.getOriginalFilename());
    try {
      Files.write(fileNameAndPath, file.getBytes());
    } catch (final IOException e) {
      e.printStackTrace();
    }
    publiciteDTO.setPicture(fileName.toString());*/
    return publiciteService.createPublicite(publiciteDTO, locationId);
  }

  @GetMapping("/{userId}/all")
  List<PubliciteDTO> showAllPublicite(@PathVariable(value = "userId") final Long userId) {
    return publiciteService.showAllPublicite(userId);
  }

  @GetMapping("/{userId}")
  List<PubliciteDTO> showAllPublicite(
      @PathVariable(value = "userId") final Long userId,
      @RequestParam(value = "category") final Category category) {
    return publiciteService.showAllPubliciteCategory(userId, category);
  }

  @GetMapping("/{userId}/")
  List<PubliciteDTO> searcheWithLocation(
      @PathVariable(value = "userId") final Long userId,
      @RequestParam(value = "locationId") final Long locationId) {
    return publiciteService.searcheWithLocation(userId, locationId);
  }

  @GetMapping("/{userId}/my")
  List<PubliciteDTO> showMyPublicite(@PathVariable(value = "userId") final Long userId) {
    return publiciteService.showMyPublicite(userId);
  }

  @PutMapping("/{pubId}/update")
  PubliciteDTO updatePublicite(
      @PathVariable final Long pubId, @Valid @RequestBody final PubliciteDTO publiciteDTO) {
    return publiciteService.updatePublicite(pubId, publiciteDTO);
  }
}
