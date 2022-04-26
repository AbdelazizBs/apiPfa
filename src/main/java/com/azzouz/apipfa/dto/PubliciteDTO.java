package com.azzouz.apipfa.dto;

// import com.azzouz.apipfa.entities.Location;

import com.azzouz.apipfa.entities.Picture;
import com.azzouz.apipfa.entities.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PubliciteDTO {
  private long id;
  private String description;
  private Date date;
  private Long userId;
  private ArrayList<Picture> picture;
  private Category category;
  private String locationCity;
}
