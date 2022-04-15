package com.azzouz.apipfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApipfaApplication {

  public static void main(final String[] args) {
    //  new File(PubliciteController.uploadDirectory).mkdir();
    SpringApplication.run(ApipfaApplication.class, args);
  }
}
