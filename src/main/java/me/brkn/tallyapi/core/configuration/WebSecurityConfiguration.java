package me.brkn.tallyapi.core.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Bean
MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(){
    return registry->registry.config().commonTags("application","MYAPPNAME");
    }

public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AuthenticationProvider authenticationProvider;

  @Autowired
  public WebSecurityConfiguration(AuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/actuator/**").permitAll()
        .antMatchers("/tally/api/**").authenticated()
        .and()
        .csrf().disable()
        .httpBasic();
  }
}
