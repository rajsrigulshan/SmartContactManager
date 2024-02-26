package com.example.SmartContactManager.Configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    @Bean
    public UserDetailsService getUserDetailService(){
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
        .csrf(csrf->csrf.disable())
        .authorizeHttpRequests((authz) -> authz
        .requestMatchers("/user/**").hasRole("NORMAL")
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().permitAll()
        ).formLogin(formLogin->formLogin.loginPage("/signin")
                                        .loginProcessingUrl("/doSignin")
                                        .permitAll())
                                        .logout(logout->logout.logoutSuccessUrl("/signout"));
                                        

        // http.
        // csrf(csrf->csrf.disable())
        // .authorizeHttpRequests((auth)->auth
        // .requestMatchers("/user/**").hasRole("NORMAL").anyRequest().permitAll()).formLogin(Customizer.withDefaults());
    return http.build();
          
    }

    

    
}
