package com.azzouz.apipfa.repositories;

import com.azzouz.apipfa.entities.Location;
import com.sun.mail.imap.protocol.ID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, ID> {

  Location findById(long locationId);
}
