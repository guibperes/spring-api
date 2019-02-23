package guizao.aula.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder encoder () {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure (HttpSecurity http) throws Exception {

    http.cors().and().csrf().disable()
      .authorizeRequests()
      .antMatchers("**").permitAll()
      .anyRequest().authenticated();

    http.headers().frameOptions().disable();
  }
}
