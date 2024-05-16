package dev.zoidnet.oauthgateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain defaultSecurityWebFilterChain(ServerHttpSecurity http, ServerLogoutSuccessHandler oidcLogoutSuccessHandler) {
        http.authorizeExchange(exchange -> exchange
                        .pathMatchers("/", "/static/**", "/api/profile", "/api/articles", "/auth/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutUrl("/oidc/logout")
                        .logoutSuccessHandler(oidcLogoutSuccessHandler));

        return http.build();
    }

    @Bean
    public ServerLogoutSuccessHandler oidcLogoutSuccessHandler(ReactiveClientRegistrationRepository repository) {
        OidcClientInitiatedServerLogoutSuccessHandler oidcClientInitiatedServerLogoutSuccessHandler = new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        oidcClientInitiatedServerLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}?logout");

        return oidcClientInitiatedServerLogoutSuccessHandler;
    }
}

