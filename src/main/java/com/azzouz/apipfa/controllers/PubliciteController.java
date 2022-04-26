package com.azzouz.apipfa.controllers;

import com.azzouz.apipfa.dto.PubliciteDTO;
import com.azzouz.apipfa.entities.enums.Category;
import com.azzouz.apipfa.services.PubliciteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/annances")
@CrossOrigin(origins = "*")
public class PubliciteController {

  public static final String uploadDirectory =
      System.getProperty("user.dir") + "/src/main/resources/static/uploads";
  @Autowired private PubliciteService publiciteService;
  //  public static String uploadDirectory = System.getProperty("user.dir") +
  // "/src/main/resources/static/uploads";

  @PostMapping("/add")
  PubliciteDTO createPublicite(
      @RequestParam(value = "description") final String description,
      @RequestParam(value = "userId") final Long userId,
      @RequestParam(value = "category") final Category category,
      @RequestParam("files") final MultipartFile[] files,
      @RequestParam(value = "locationCity") final String locationCity)
      throws IOException {

    /* final StringBuilder fileName = new StringBuilder();
    final MultipartFile file = files[0];

    final Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

    fileName.append(file.getOriginalFilename());
    try {
      Files.write(fileNameAndPath, file.getBytes());
    } catch (final IOException e) {
      e.printStackTrace();
    }
    final String fileN = fileName.toString();*/
    return publiciteService.createPublicite(description, userId, category, files, locationCity);
  }

  @GetMapping("/all/{userId}")
  List<PubliciteDTO> showAllPublicite(@PathVariable(value = "userId") final Long userId) {
    return publiciteService.showAllPublicite(userId);
  }

  @GetMapping("/withCategory/{userId}")
  List<PubliciteDTO> showAllPubliciteWithCategory(
      @PathVariable(value = "userId") final Long userId,
      @RequestParam(value = "category") final Category category) {
    return publiciteService.showAllPubliciteCategory(userId, category);
  }

  @GetMapping("/withLocation/{userId}")
  List<PubliciteDTO> searcheWithLocation(
      @PathVariable(value = "userId") final Long userId,
      @RequestParam(value = "locationCity") final String locationCity) {
    return publiciteService.searcheWithLocation(userId, locationCity);
  }

  @GetMapping("/my/{userId}")
  List<PubliciteDTO> showMyPublicite(@PathVariable(value = "userId") final Long userId) {
    return publiciteService.showMyPublicite(userId);
  }

  @PutMapping("/update/{pubId}")
  PubliciteDTO updatePublicite(
      @PathVariable final Long pubId,
      @RequestParam(value = "description") final String description,
      @RequestParam(value = "userId") final Long userId,
      @RequestParam(value = "category") final Category category,
      @RequestParam("files") final MultipartFile[] files,
      @RequestParam(value = "locationCity") final String locationCity)
      throws IOException {

    /*
            final StringBuilder fileName = new StringBuilder();
            final MultipartFile file = files[0];
            final Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
            fileName.append(file.getOriginalFilename());
            try {
              Files.write(fileNameAndPath, file.getBytes());
            } catch (final IOException e) {
              e.printStackTrace();
            }
            final String fileN = fileName.toString();
    */
    return publiciteService.updatePublicite(
        pubId, description, userId, category, files, locationCity);
  }
}
