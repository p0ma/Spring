package com.springapp.mvc.configuration;

import com.springapp.mvc.auth.MyAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import service.MyUserDetailsService;

import javax.servlet.ServletContext;


@EnableWebMvcSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RememberMeServices rememberMeServices;

    @Autowired
    UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;

    @Autowired
    OpenEntityManagerInViewFilter openEntityManagerInViewFilter;

    @Autowired
    CharacterEncodingFilter characterEncodingFilter;

    @Autowired
    public void addFilters(ServletContext servletContext) {
        servletContext
                .addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/login/*", "/logout/*", "/parameters/*", "/auth/*", "/chart/*", "/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider authenticationProvider)
            throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(usernamePasswordAuthenticationFilter, RememberMeAuthenticationFilter.class)
                .addFilterBefore(characterEncodingFilter, WebAsyncManagerIntegrationFilter.class)
                .addFilterBefore(openEntityManagerInViewFilter, WebAsyncManagerIntegrationFilter.class)
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").and()
                .csrf().disable()
                .rememberMe().rememberMeServices(rememberMeServices).tokenValiditySeconds(1209600);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter() {
        return new OpenEntityManagerInViewFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new MyAuthenticationManager();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager) {
        UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter =
                new UsernamePasswordAuthenticationFilter();
        usernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return usernamePasswordAuthenticationFilter;
    }

   /* @Bean
    public RememberMeAuthenticationFilter rememberMeAuthenticationFilter(
            AuthenticationManager authenticationManager,
            PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices) {
        return new RememberMeAuthenticationFilter(authenticationManager,
                persistentTokenBasedRememberMeServices);
    }*/

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DriverManagerDataSource dataSource) {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices(
            PersistentTokenRepository persistentTokenRepository, MyUserDetailsService myUserDetailsService) {
        PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices =
                new PersistentTokenBasedRememberMeServices("uniqueKey1", myUserDetailsService,
                        persistentTokenRepository);
        persistentTokenBasedRememberMeServices.setTokenValiditySeconds(1209600);
        persistentTokenBasedRememberMeServices.setAlwaysRemember(true);
        return persistentTokenBasedRememberMeServices;
    }
}