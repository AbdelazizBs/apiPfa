package com.azzouz.apipfa.services;
/*
import com.azzouz.apipfa.entities.Picture;
import com.azzouz.apipfa.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileStorageService {
  @Autowired private PictureRepository pictureRepository;

  public Picture store(final MultipartFile file) throws IOException {
    final String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    final Picture FileDB = new Picture(fileName, file.getBytes());
    return pictureRepository.save(FileDB);
  }

  public Picture getFile(final Long id) {
    return pictureRepository.findById(id).get();
  }

  public Stream<Picture> getAllFiles() {
    return pictureRepository.findAll().stream();
  }
}
*/
