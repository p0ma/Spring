package com.springapp.mvc.configuration;

import com.springapp.mvc.auth.MyAuthenticationProvider;
import com.springapp.mvc.context.provider.ApplicationContextProvider;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@ImportResource(value = "classpath:spring.xml")
@Import({SecurityConfig.class})
@ComponentScan(basePackages = {"service", "com.springapp.mvc", "entities.drilling.parameters"})
@EnableWebMvc
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = {"repositories"})
@EnableTransactionManagement
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    @Resource
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            String url = "jdbc:mysql://" + environment.getProperty("OPENSHIFT_MYSQL_DB_HOST") + ":"
                    + environment.getProperty("OPENSHIFT_MYSQL_DB_PORT") + "/" + environment.getProperty("OPENSHIFT_APP_NAME");
            dataSource.setUrl(url);
            dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
            dataSource.setUsername(environment.getRequiredProperty("OPENSHIFT_MYSQL_DB_USERNAME"));
            dataSource.setPassword(environment.getRequiredProperty("OPENSHIFT_MYSQL_DB_PASSWORD"));
        } catch (IllegalStateException e) {
            dataSource.setUrl(environment.getRequiredProperty("db.url"));
            dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
            dataSource.setUsername(environment.getRequiredProperty("db.username"));
            dataSource.setPassword(environment.getRequiredProperty("db.password"));
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty("entitymanager.packages.to.scan"));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.ejb.naming_strategy", environment.getRequiredProperty("hibernate.ejb.naming_strategy"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));


        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        return entityManagerFactoryBean;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames(
                "classpath:messages/app",
                "classpath:messages/jsp",
                "classpath:messages/parameter",
                "classpath:messages/chart",
                "classpath:messages/months",
                "classpath:messages/weekdays",
                "classpath:messages/errors",
                "classpath:messages/validation",
                "classpath:messages/pipeSection");

        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("myAppLocaleCookie");
        localeResolver.setCookieMaxAge(3600);
        return new CookieLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return new LocaleChangeInterceptor();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }
/*
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.enableContentNegotiation(new MappingJackson2JsonView());
        registry.viewResolver(viewResolver());
    }*/

/*    @Bean(name = "viewResolver")
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");


        return viewResolver;
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(localeChangeInterceptor());
    }

   /* @Bean
    public SessionLocaleResolver sessionLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return sessionLocaleResolver;
    }*/

    @Bean
    public ApplicationContextProvider getApplicationContextProvider() {
        return new ApplicationContextProvider();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new MyAuthenticationProvider();
    }

    /*@Bean
    public RememberMeServices rememberMeServices(MyUserDetailsService myUserDetailsService) {
        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices();
        rememberMeServices.setUserDetailsService(myUserDetailsService);
        return rememberMeServices;
    }

    @Bean
    public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider();
    }

    @Bean
    RememberMeAuthenticationFilter rememberMeAuthenticationFilter(
            RememberMeServices rememberMeServices, AuthenticationManager authenticationManager) {
        RememberMeAuthenticationFilter rememberMeAuthenticationFilter = new RememberMeAuthenticationFilter();
        rememberMeAuthenticationFilter.setRememberMeServices(rememberMeServices);
        rememberMeAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return rememberMeAuthenticationFilter;
    }*/


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(messageConverters);
    }

    private HttpMessageConverter<Object> createXmlHttpMessageConverter() {
        MarshallingHttpMessageConverter xmlConverter =
                new MarshallingHttpMessageConverter();

        XStreamMarshaller xstreamMarshaller = new XStreamMarshaller();
        xmlConverter.setMarshaller(xstreamMarshaller);
        xmlConverter.setUnmarshaller(xstreamMarshaller);

        return xmlConverter;
    }
}
