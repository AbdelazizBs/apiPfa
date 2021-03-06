package com.azzouz.apipfa.controllers;

import com.azzouz.apipfa.entities.Location;
import com.azzouz.apipfa.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "*")
public class LocationController {

  @Autowired private LocationRepository locationRepository;

  @GetMapping("/get/{locationId}")
  Location getLocation(@PathVariable(value = "locationId") final Long locationId) {
    return locationRepository.findById(locationId);
  }

  @GetMapping("/getCityLocation/{locationId}")
  String getSpeceficLocationWithId(@PathVariable(value = "locationId") final Long locationId) {
    return locationRepository.findById(locationId).getCity();
  }

  @GetMapping("/getAllCityLocation")
  List<String> getCityLocation() {
    return locationRepository.findCity();
  }
}
