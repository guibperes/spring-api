package guizao.aula.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import guizao.aula.auth.AuthFilter;
import guizao.aula.auth.UnauthorizedAuthEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UnauthorizedAuthEntryPoint unauthorizedHandler;

  @Bean
  public BCryptPasswordEncoder encoder () {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthFilter authFilter () throws Exception {
    return new AuthFilter();
  }

  @Override
  protected void configure (HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
      .authorizeRequests()
      .antMatchers("**").permitAll()
      .anyRequest().authenticated()
      .and()
      .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    http.headers().frameOptions().disable();
  }
}
