package com.azzouz.apipfa.configurations;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class InitializeData {

  @Autowired private DataSource dataSource;

  @EventListener(ApplicationReadyEvent.class)
  public void loadData() {
    final ResourceDatabasePopulator resourceDatabasePopulator =
        new ResourceDatabasePopulator(
            false, false, "UTF-8", new ClassPathResource("/location.sql"));
    resourceDatabasePopulator.execute(dataSource);
  }
}
*/
// faite le creation aprés faite le param de base utf8mb..  et faire update
