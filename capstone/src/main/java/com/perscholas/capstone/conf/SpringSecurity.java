package com.perscholas.capstone.conf;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.SecurityContextConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableWebSecurity



public class SpringSecurity {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

  

  //  @SuppressWarnings({ "removal", "deprecation" })
   	@Bean
   	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	         http
	         .authorizeRequests() 
                .requestMatchers("/","/home", "/user","/aboutus",  "/resources/templates/**", "/anime/**").permitAll()
                .requestMatchers("/resources/**", "/static/**", "/css/**", "/fonts/**", "/images/**", "/js/**", "/sass/**" ).permitAll()
                .requestMatchers("/form_submission", "/index").permitAll()
                .requestMatchers("/registration/**").permitAll()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/submissions").hasRole("ADMIN")
                .requestMatchers("/favorites").authenticated() // The favorites page can be accessed by authenticated users
                .requestMatchers("/favorite/add").hasAnyRole("USER", "ADMIN")   
                .requestMatchers(HttpMethod.GET, "/comments").permitAll() // Allow GET requests to /comments without authentication
                .requestMatchers(HttpMethod.POST, "/comments").authenticated() //
                .requestMatchers(HttpMethod.POST, "/comments").permitAll() 
                //.requestMatchers("/submissions").hasRole("ADMIN")
                //.requestMatchers("/submit").permitAll() // Restrict access to /submit to authenticated users
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/profile") 
                    .permitAll()
                .and()
                .logout()
                    .permitAll()
                .and()

                .exceptionHandling()
                    .accessDeniedPage("/access-denied")
                    
                    
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .invalidSessionUrl("/login?expired=true")
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .expiredUrl("/login?expired=true")
                    
                    ;
           
	         CorsConfiguration corsConfiguration = new CorsConfiguration();
	         corsConfiguration.addAllowedOrigin("http://localhost:8080"); 
	         corsConfiguration.addAllowedMethod("*"); 
	         corsConfiguration.addAllowedHeader("*"); 
	         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	         source.registerCorsConfiguration("/api/series/{id}", corsConfiguration);
	         http.addFilterBefore(new CorsFilter(source), UsernamePasswordAuthenticationFilter.class);
                
                return http.build();
	           
	}
    
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    


}

  

   

