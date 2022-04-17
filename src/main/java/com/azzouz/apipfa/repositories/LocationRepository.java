package com.azzouz.apipfa.repositories;

import com.azzouz.apipfa.entities.Location;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, ID> {

  Location findById(long locationId);

  Location findByCity(String city);

  public static final String GET_CITY = "SELECT city FROM location";

  @Query(value = GET_CITY, nativeQuery = true)
  public List<String> findCity();
}
