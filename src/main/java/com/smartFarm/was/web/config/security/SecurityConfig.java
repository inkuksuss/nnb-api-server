package com.smartFarm.was.web.config.security;

import com.smartFarm.was.domain.entity.sub.Authority;
import com.smartFarm.was.web.config.security.filter.JwtFilter;
import com.smartFarm.was.web.config.security.provider.TokenProvider;
import com.smartFarm.was.web.service.impl.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtAccessDeniedHandler jwtAccessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(tokenProvider);
    }

    @Bean
    public JwtAuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(memberService, this.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()

                .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole(Authority.ADMIN.getAlias())
                    .antMatchers("/login", "/join").hasRole(Authority.ANONYMOUS.getAlias())
                    .antMatchers("/**").hasAnyRole(Authority.MEMBER.getAlias(), Authority.ANONYMOUS.getAlias())

                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                    .headers().frameOptions().sameOrigin()

                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                    .accessDeniedHandler(new JwtAccessDeniedHandler())

                .and()
                    .anonymous()
                    .principal(Authority.ANONYMOUS.getAlias())
                    .authorities(Authority.ANONYMOUS.getRole())

                .and()
                    .authenticationProvider(jwtAuthenticationProvider());
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8081")
                .allowCredentials(true);
    }
}
