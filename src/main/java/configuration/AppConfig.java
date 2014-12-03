package configuration;

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
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


@ComponentScan(basePackages = {"service", "com.springapp.mvc", "entities.drilling.model.parameters"})
@EnableWebMvc
@PropertySource("classpath:application.properties")
@Import({SecurityConfig.class})
@EnableJpaRepositories(basePackages = {"repositories"})
@EnableTransactionManagement
@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {
    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/pages/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    private static final String PROPERTY_NAME_HIBERNATE_AUTOCREATE = "hibernate.hbm2ddl.auto";

    private static final String PROPERTY_NAME_MESSAGESOURCE_BASENAME = "message.source.basename";
    private static final String PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE = "message.source.use.code.as.default.message";

    /*@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> httpMessageConverters) {
        httpMessageConverters.add(new ChartDataMessageConverter(new MediaType("text", "csv")));
    }*/
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactory().getObject());
        return transactionManager;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.add(new MappingJackson2HttpMessageConverter());
//        messageConverters.add(createXmlHttpMessageConverter());
//        messageConverters.add(new ChartDataMessageConverter(new MediaType("text", "csv")));
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

    @Resource
    private Environment environment;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        try {
            dataSource.setUrl(environment.getRequiredProperty("OPENSHIFT_MYSQL_DB_USERNAME"));
            dataSource.setDriverClassName(environment.getRequiredProperty("OPENSHIFT_MYSQL_DB_USERNAME"));
            dataSource.setUsername(environment.getRequiredProperty("OPENSHIFT_MYSQL_DB_USERNAME"));
            dataSource.setPassword(environment.getRequiredProperty("OPENSHIFT_MYSQL_DB_USERNAME"));
        } catch (IllegalStateException e) {
            dataSource.setUrl(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
            dataSource.setDriverClassName(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
            dataSource.setUsername(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
            dataSource.setPassword(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        }
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties jpaProperties = new Properties();
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        jpaProperties.put(PROPERTY_NAME_HIBERNATE_AUTOCREATE, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_AUTOCREATE));


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
        messageSource.setUseCodeAsDefaultMessage(Boolean.parseBoolean(environment.getRequiredProperty(PROPERTY_NAME_MESSAGESOURCE_USE_CODE_AS_DEFAULT_MESSAGE)));

        return messageSource;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);


        return viewResolver;
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("myAppLocaleCookie");
        localeResolver.setCookieMaxAge(3600);
        return new CookieLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("locale");
        return new LocaleChangeInterceptor();
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
}
