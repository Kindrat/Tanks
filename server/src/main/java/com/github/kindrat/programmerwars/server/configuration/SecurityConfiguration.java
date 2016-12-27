package com.github.kindrat.programmerwars.server.configuration;

import com.github.kindrat.programmerwars.server.dto.Role;
import com.github.kindrat.programmerwars.server.persistence.entity.Player;
import com.github.kindrat.programmerwars.server.persistence.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Optional;

import static java.util.Collections.singletonList;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PlayerRepository playerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and().authorizeRequests()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.css").permitAll()
                .antMatchers("/*.less").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(playerRepository));
    }

    @RequiredArgsConstructor
    private static class CustomAuthenticationProvider implements AuthenticationProvider {
        private final PlayerRepository repository;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            String name = authentication.getName();
            String password = authentication.getCredentials().toString();

            return Optional.ofNullable(repository.findByCredentials(name, password))
                    .map(this::mapPlayer)
                    .orElse(null);
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return authentication.equals(UsernamePasswordAuthenticationToken.class);
        }

        private UsernamePasswordAuthenticationToken mapPlayer(Player player) {
            Role userRole = player.getUserGroup().getUserRole();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + userRole);
            return new UsernamePasswordAuthenticationToken(player.getLogin(), player.getPassword(),
                    singletonList(authority));
        }
    }

}
