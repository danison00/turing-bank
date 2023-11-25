package dan.turingbank.infra.webSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private SecurityFilter securityFilter;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                return http.cors(cors->cors.disable()).csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                .authorizeHttpRequests(authorize -> authorize
                                               .requestMatchers("/").permitAll()
                                               .requestMatchers("/api-public/**").permitAll()
                                                .requestMatchers("/api-public/**").permitAll()
                                                .requestMatchers("/img/**", "/js/**", "/css/**", "/bootstrap-5.3.2-dist/**").permitAll()
                                                .requestMatchers("/turing-bank", "/deposito", "/api/transaction/deposit/**", "/api/transaction/deposit").permitAll()
                                                .requestMatchers("/criar-conta").permitAll()
                                                .requestMatchers("/api/username-not-exists/**").permitAll()
                                                //.requestMatchers("/home").permitAll()
                                                // .requestMatchers(HttpMethod.DELETE, "/account").hasRole("USER")
                                                // .requestMatchers(HttpMethod.POST, "/api/account").permitAll()
                                                // .requestMatchers("/transaction/deposit").permitAll()
                                                // .requestMatchers("/transaction/transfer").hasRole("USER")
                                                // .requestMatchers("api/user/**").permitAll()
                                                // .requestMatchers("/login").permitAll()
                                                // .requestMatchers("/api/login").permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(login -> login
                                                .loginPage("/login") // Página de login
                                                .defaultSuccessUrl("/home") // Página após o login bem-sucedido
                                                .permitAll())
                                .exceptionHandling(exception -> exception
                                                .authenticationEntryPoint(
                                                                (request, response, authException) -> response
                                                                                .sendRedirect("/login")))

                                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();

        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {

                return authenticationConfiguration.getAuthenticationManager();

        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
