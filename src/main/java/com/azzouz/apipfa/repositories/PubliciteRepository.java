package com.azzouz.apipfa.repositories;

import com.azzouz.apipfa.entities.Publicite;
import com.azzouz.apipfa.entities.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PubliciteRepository extends JpaRepository<Publicite, Long> {

  List<Publicite> findByUser_Id(Long id);

  List<Publicite> findByCategoryAndUser_Id(Category category, Long id);

  List<Publicite> findByLocationId(Long locationId);
}
