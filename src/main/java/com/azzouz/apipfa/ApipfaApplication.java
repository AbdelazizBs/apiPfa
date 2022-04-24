package com.azzouz.apipfa;

import com.azzouz.apipfa.controllers.PubliciteController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ApipfaApplication {

  public static void main(final String[] args) {
    new File(PubliciteController.uploadDirectory).mkdir();
    SpringApplication.run(ApipfaApplication.class, args);
  }
}
