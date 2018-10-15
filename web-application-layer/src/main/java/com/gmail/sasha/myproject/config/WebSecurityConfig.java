package com.gmail.sasha.myproject.config;

import com.gmail.sasha.myproject.config.handlers.AppSuccessHandler;
import com.gmail.sasha.myproject.config.handlers.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("appSuccessHandler")
    private AppSuccessHandler appSuccessHandler;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login**").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/registration").permitAll()
                // .antMatchers("/users").permitAll()
                //.antMatchers("/users").hasAuthority()

                .antMatchers("/**").permitAll()

                .anyRequest().fullyAuthenticated()

                .and()
                .formLogin()

                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(appSuccessHandler)
                .failureUrl("/login?error=true")
                .permitAll()

                .and()
                .logout()

                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()

            /*    .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)*/

                .and()
                .csrf().disable();
    }

    @Bean("passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); //Remove the "Role_" prefix
    }
}
