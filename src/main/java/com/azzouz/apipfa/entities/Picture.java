package com.azzouz.apipfa.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Picture")
@Getter
@Setter
@NoArgsConstructor
public class Picture {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "fileName")
  private String fileName;

  @Column @Lob private byte[] bytes;

  public Picture(final String fileName, final byte[] bytes) {
    this.fileName = fileName;
    this.bytes = bytes;
  }
}
