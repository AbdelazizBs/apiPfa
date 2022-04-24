package com.azzouz.apipfa.repositories;

import com.azzouz.apipfa.entities.Publicite;
import com.azzouz.apipfa.entities.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PubliciteRepository extends JpaRepository<Publicite, Long> {
  @Query("select p from Publicite p where p.location.city = ?1 ORDER BY p.date DESC ")
  List<Publicite> findByLocationOrdred(String city);

  List<Publicite> findByLocation_City(String city);

  List<Publicite> findByUser_Id(Long id);

  List<Publicite> findByPicture_Id(long id);

  //  List<Publicite> findByUser_Id(Long id);

  List<Publicite> findByCategoryAndUser_Id(Category category, Long id);

  List<Publicite> findByLocationId(Long locationId);

  public static final String GET_PUBLICITYSHARED = "select * from   publicite  where user_id != ? ";

  @Query(value = GET_PUBLICITYSHARED, nativeQuery = true)
  public List<Publicite> findPubliciteShared(Long id);
}
