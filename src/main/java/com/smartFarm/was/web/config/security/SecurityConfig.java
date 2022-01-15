package com.smartFarm.was.web.config.security;

import com.smartFarm.was.web.config.security.filter.JwtFilter;
import com.smartFarm.was.web.config.security.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(tokenProvider);
    }

//    @Bean
//    public AjaxAuthenticationProvider ajaxAuthenticationProvider() {
//        return new AjaxAuthenticationProvider(userDetailsService, passwordEncoder());
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        return new FormAuthenticationProvider();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
//        return new AjaxAuthenticationSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
//        return new AjaxAuthenticationFailHandler();
//    }
//
//    @Bean
//    public AuthenticationSuccessHandler authenticationSuccessHandler() {
//        return new FormAuthenticationSuccessHandler();
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return new FormAuthenticationFailureHandler();
//    }
//
//    @Bean
//    public LogoutSuccessHandler logoutSuccessHandler() {
//        return new CommonLogoutSuccessHandler();
//    }
//
//    @Bean
//    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() throws Exception {
//        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
//        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManagerBean());
//        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
//        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
//        return ajaxLoginProcessingFilter;
//    }
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(ajaxAuthenticationProvider());
//        auth.authenticationProvider(authenticationProvider());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()

                .and()
                    .csrf().disable()
                    .antMatcher("/**")
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll()

                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                    .headers().frameOptions().sameOrigin();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);

//                .and()
//                .logout()
//                .logoutUrl("/logout")
////                .logoutSuccessUrl("/")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "SESSION")
//                .clearAuthentication(true)
//                .permitAll()

//                .and()
//                .addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://192.168.0.49:8080")
                .allowCredentials(true);
    }

//    public AccessDeniedHandler accessDeniedHandler() {
//        return new AjaxAccessDeniedHandler();
//    }
}
