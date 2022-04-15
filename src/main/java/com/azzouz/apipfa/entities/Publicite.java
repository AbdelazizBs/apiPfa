package com.azzouz.apipfa.entities;

import com.azzouz.apipfa.entities.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "publicite")
@Getter
@Setter
@NoArgsConstructor
public class Publicite {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "description")
  private String description;

  @Column(name = "picture", nullable = true, length = 64)
  private String picture;

  @Column(name = "date")
  private Date date;

  @Enumerated(EnumType.STRING)
  @Column(name = "category")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private User user;

  @ManyToOne
  @JoinColumn(name = "location_id" /*, nullable = false*/)
  private Location location;
}
