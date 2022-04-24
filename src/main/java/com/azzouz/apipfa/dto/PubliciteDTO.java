package com.azzouz.apipfa.dto;

// import com.azzouz.apipfa.entities.Location;

import com.azzouz.apipfa.entities.Picture;
import com.azzouz.apipfa.entities.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PubliciteDTO {
  private long id;
  private String description;
  private Date date;
  private Long userId;
  private List<Picture> picture;
  private Category category;
  private String locationCity;
}
