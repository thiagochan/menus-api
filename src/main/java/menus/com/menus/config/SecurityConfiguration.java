package menus.com.menus.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserAuthenticationFilter userAuthenticationFilter;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/index.html",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/index.css",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/webjars/**",
            "/swagger-resources/**",
            "/users/login", //url que usaremos para fazer login
            "/users" //url que usaremos para criar um usuário
    };
    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/project/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(csrf -> csrf.disable()) // Desativa CSRF
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll() // Permite acesso livre
                        .requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated()// Exige autenticação
                        .anyRequest().authenticated()
                )
                .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona filtro de autenticação
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
