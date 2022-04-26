package com.azzouz.apipfa.repositories;

import com.azzouz.apipfa.entities.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
  boolean existsByFileName(String fileName);

  boolean existsByBytes(byte[] bytes);

  List<Picture> findByFileNameAndBytes(String fileName, byte[] bytes);
}
