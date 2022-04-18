package com.azzouz.apipfa.configurations;
/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        //configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.addAllowedOriginPattern("*");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().
                disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}

//another method


/*public class SecurityConfig extends WebSecurityConfigurerAdapter {
@Override
protected void configure(HttpSecurity http) throws Exception {
http.csrf().disable().
authorizeRequests().antMatchers(HttpMethod.OPTIONS,
"/**").permitAll().anyRequest().authenticated()
.and().httpBasic();
}
@Autowired
public void configureGlobal(AuthenticationManagerBuilder auth) throws
Exception {
auth.inMemoryAuthentication().withUser("mohamed").password("{noop}1234").roles
("USER");
}
}
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
  @Autowired private DataSource dataSource;

  @Value("${spring.queries.users-query}")
  private String usersQuery;

  @Value("${spring.queries.roles-query}")
  private String rolesQuery;

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    // configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setExposedHeaders(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("*"));
    configuration.addAllowedOriginPattern("*");
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication()
        .usersByUsernameQuery(usersQuery)
        .authoritiesByUsernameQuery(rolesQuery)
        .dataSource(dataSource);
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.cors();
    http.authorizeRequests()
        .antMatchers("/users/**")
        .permitAll() // accès pour tous users
        .antMatchers("/users/login")
        .permitAll() // accès pour tous users
        .antMatchers("/users/registre")
        .permitAll() // accès pour tous users
        .antMatchers("/users/update")
        .permitAll() // accès pour tous users
        .antMatchers("/location/**")
        .permitAll() // accès pour tous users
        .antMatchers("/annances/**")
        .permitAll() // accès pour tous users
        .antMatchers("/annances/my/**")
        .permitAll() // accès pour tous users
        // .antMatchers("/provider/**").hasAuthority("ADMIN")
        /*  .antMatchers("/users/enable").hasAuthority("ADMIN")
        .antMatchers("/users/disable").hasAuthority("ADMIN")*/
        /*.antMatchers("/annances/**")
        .hasAuthority("USER")
        .anyRequest()
        .authenticated()*/
        .and()
        .csrf()
        .disable()
        .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error=true") // fixer la page login
        .defaultSuccessUrl("/acceuil") // page d'accueil après login avec succès
        .usernameParameter("username") // paramètres d'authentifications login et password
        .passwordParameter("password")
        .and()
        .logout()
        .logoutRequestMatcher(
            new AntPathRequestMatcher("/logout")) // route de deconnexion ici/logut
        .logoutSuccessUrl("/login")
        .and()
        .exceptionHandling() // une fois deconnecté redirection vers login
        .accessDeniedPage("/403");
  }

  // laisser l'accès aux ressources
  @Override
  public void configure(final WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
  }
}
