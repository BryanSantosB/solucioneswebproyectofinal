package proyecto.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import proyecto.demo.Model.service.UserService;

@Configuration
public class SpringSecurityConfig {
    
    @Autowired
    private UserService userService;

    // public static BCryptPasswordEncoder encriptarPassword(){
    //     return new BCryptPasswordEncoder();
    // }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void criptografiaPassword(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/autenticar/").defaultSuccessUrl("/inicio/").permitAll()
            .and()
            .logout().permitAll();
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return (web)->web.ignoring().antMatchers("/inicio/","/css/**","/js/**","/img/**","/vendor/**","/registrarse");
    }
}
