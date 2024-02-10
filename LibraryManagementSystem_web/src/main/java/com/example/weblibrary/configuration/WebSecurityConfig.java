package com.example.weblibrary.configuration;

import com.example.weblibrary.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/registration").permitAll()
//                    .antMatchers(HttpMethod.POST, "/recommend_book/**").hasAnyAuthority("ADMIN", "USER")
//                    .antMatchers(HttpMethod.POST, "/**").hasAuthority("ADMIN")
//                    .antMatchers(HttpMethod.DELETE, "/**").hasAuthority("ADMIN")
//                    .antMatchers(HttpMethod.GET, "/**").hasAnyAuthority("ADMIN", "USER")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }


//        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                    .authorizeRequests()
//                    .antMatchers("/recommended_books/{id}/remove", "/all_books/add", "/book/{id}/remove").hasAuthority("ADMIN")
//                    .antMatchers("/registration").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT username, password, active FROM usr WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, ur.roles FROM usr u inner JOIN user_role ur on u.id = ur.user_id WHERE u.username=? ");
    }

}
