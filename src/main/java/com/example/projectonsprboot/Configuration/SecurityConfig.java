package com.example.projectonsprboot.Configuration;

import com.example.projectonsprboot.Model.Person;
import com.example.projectonsprboot.Service.CustomPersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    CustomPersService customPersService;
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomPersService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(new AntPathRequestMatcher("/resources/**"),
                                new AntPathRequestMatcher("/static/**"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/templates/**"),
                                new AntPathRequestMatcher("/js/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/create")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/changepassw")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/change-password")).permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .failureUrl("/authorization?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .successHandler(((request, response, authentication) ->
                        {
                            if (authentication != null && authentication.isAuthenticated()) {
                                Object principal = authentication.getPrincipal();
                                if (principal instanceof Person) {
                                    Person person = (Person) principal;
                                    System.out.println("Logged user: " + person.getUsername());
                                    System.out.println(person);
                                }
                                response.sendRedirect("/rewtry");
                            }
                        })))
                .logout(logout -> logout
                        .logoutSuccessUrl("/logout_success?agree")
                        .permitAll()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            System.out.println("This user was logout" + authentication.getPrincipal());
                            response.sendRedirect("/logout_success");
                        }));
        return http.build();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customPersService);
    }//тут вказуємо фреймворку як використовувати мою реалізуцію UserDetailsService
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoauthenticationProvider= new DaoAuthenticationProvider();
        daoauthenticationProvider.setUserDetailsService(userDetailsService());
        daoauthenticationProvider.setPasswordEncoder(encoder());
        return daoauthenticationProvider;
    }//DaoAuthenticationProvider використовує об'єкт типу UserDetailsService та PasswordEncoder для проведення аутентифікації користувачів.
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
