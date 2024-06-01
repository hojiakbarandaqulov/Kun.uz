package dasturlash.uz.config;

import dasturlash.uz.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // authentication
       /* String password = UUID.randomUUID().toString();
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
        return authenticationProvider;*/
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
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
                    .requestMatchers("email/adm/**").permitAll()
                    .anyRequest()
                    .authenticated();
        });
        //http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() { //
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5Util.getMD5(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}
