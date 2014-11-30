package configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletContext;


@EnableWebMvcSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void addFilters(ServletContext servletContext) {

        CharacterEncodingFilter encoding = new CharacterEncodingFilter();
        encoding.setEncoding("UTF-8");
        encoding.setForceEncoding(true);

        servletContext.addFilter("encodingFilter", new CharacterEncodingFilter())
                .addMappingForUrlPatterns(null, false, "/*");

        servletContext.addFilter("openEntityManagerInViewFilter", new OpenEntityManagerInViewFilter())
                .addMappingForUrlPatterns(null, false, "/*");
        servletContext
                .addFilter("springSecurityFilterChain",
                        new DelegatingFilterProxy("springSecurityFilterChain"))
                .addMappingForUrlPatterns(null, false, "/*");
    }

    @Autowired
    public void configureGlobal(AuthenticationProvider authenticationProvider, AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .rememberMe().key("secretKey").and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("JSESSIONID").and()
                .authorizeRequests()
                .antMatchers("/logout/**").access("hasRole('ROLE_USER')");
    }
}