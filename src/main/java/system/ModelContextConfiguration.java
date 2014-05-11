package system;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import system.drilling.model.Model;

@ComponentScan(basePackages={"system.drilling.model.*"})
@Configuration
public class ModelContextConfiguration {
}
