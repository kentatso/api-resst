package com.api.loginService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userService;

  public SecurityConfig(UserDetailsServiceImpl userService) {
    this.userService = userService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    //    http.csrf()
    //        .disable()
    //        .authorizeRequests()
    //        .antMatchers(HttpMethod.GET, "/login")
    //        .permitAll()
    //        .and()
    //        .authorizeHttpRequests()
    //        .antMatchers(HttpMethod.POST, "/api")
    //        .permitAll()
    //        .anyRequest()
    //        .authenticated()
    //        .and()
    //        .addFilter(new JWTAuthenticateFilter(authenticationManager()))
    //        .addFilter(new JWTValidateFilter(authenticationManager()))
    //        .sessionManagement()
    //        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    //        http.csrf().disable();
    //        http.sessionManagement().sessionCreationPolicy(STATELESS);
    //        http.authorizeHttpRequests().antMatchers("/login").permitAll();
    //        http.authorizeHttpRequests().antMatchers(POST,
    // "/api/createUser/**").hasAnyAuthority("**");

    //        http.httpBasic().disable();
    //        http.authorizeRequests().antMatchers("/api/**").permitAll().antMatchers("/*")
    //                .fullyAuthenticated().and().formLogin()
    //                .and().csrf().disable();
    //        http.csrf().disable()
    //                .addFilter(new JWTAuthenticateFilter(authenticationManager()))
    //                .addFilter(new JWTValidateFilter(authenticationManager()));

    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(
            HttpMethod.POST,
            "/api/**",
            "/login",
            "/user",
            "/swagger**",
            "/swagger-ui.html",
            "/swagger-ui.html#",
            "/swagger-ui.html#/",
            "/v2/api-docs",
            "/user/image",
            "swagger-ui/index.html#/",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .addFilter(new JWTAuthenticateFilter(authenticationManager()))
        .addFilter(new JWTValidateFilter(authenticationManager()))
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
