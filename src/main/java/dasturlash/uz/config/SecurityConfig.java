package dasturlash.uz.config;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.UUID;

@EnableWebSecurity
@Component
public class SecurityConfig {
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication
        String password = UUID.randomUUID().toString();
        System.out.println("User Pasword: " + password);

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}" + password)
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}adminjon")
                .roles("ADMIN")
                .build();
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(new InMemoryUserDetailsManager(user, admin));
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // authorization
       /* http.authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and().formLogin();*/
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
            authorizationManagerRequestMatcherRegistry
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/auth/registration").hasRole("USER")
                    .requestMatchers("/auth/login").permitAll()
                    .requestMatchers("/profile/adm/**").hasRole("ADMIN")
                    .requestMatchers("/profile/filter").permitAll()
                    .requestMatchers("/region/lang").permitAll()
                    .requestMatchers("/region/adm/**").hasRole("ADMIN")
                    .requestMatchers("/types/adm/**").hasRole("ADMIN")
                    .requestMatchers("types/language").permitAll()
                    .requestMatchers("category/adm/**").hasRole("ADMIN")
                    .requestMatchers("category/language").permitAll()
                    .requestMatchers("/api/history/*").permitAll()
                    .requestMatchers("/api/create/*").hasRole("MODERATOR")
                    .anyRequest()
                    .authenticated();
        });
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
