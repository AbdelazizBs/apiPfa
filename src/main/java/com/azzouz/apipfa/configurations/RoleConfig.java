package com.azzouz.apipfa.configurations;

import com.azzouz.apipfa.entities.Role;
import com.azzouz.apipfa.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RoleConfig {
  @Autowired private RoleRepository roleRepository;

  @EventListener
  public void appReady(final ApplicationReadyEvent event) {
    roleRepository.save(new Role(1, "USER"));
    roleRepository.save(new Role(2, "ADMIN"));
  }
}
