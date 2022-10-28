//package com.example.restaurant.security;
//
//import com.example.restaurant.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.http.HttpServletResponse;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfigurer {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http = http.cors().and().csrf().disable();
//
//        http = http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and();
//
//        http = http
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        ((request, response, authException) -> {
//                            System.out.println("Unauthorized request");
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
//                        })
//                )
//                .and();
//
//        http.authorizeRequests()
//                .antMatchers("/auth/**").permitAll()
//                .anyRequest().permitAll();
//
//        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.eraseCredentials(false)
//                .userDetailsService(userService)
//                .passwordEncoder(passwordEncoder);
//
//        return authenticationManagerBuilder.build();
//    }
//
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
////        return authenticationConfiguration.getAuthenticationManager();
////    }
////
////    @Bean
////    @Primary
////    public AuthenticationManagerBuilder configureAuthenticationManagerBuilder(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
////        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////        return authenticationManagerBuilder;
////    }
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.cors().and().csrf().disable()
////                .exceptionHandling().authenticationEntryPoint(authEntryPointJwt).and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
////                .antMatchers("/api/test/**").permitAll()
////                .anyRequest().authenticated();
////        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
////        return http.build();
////    }
//}
