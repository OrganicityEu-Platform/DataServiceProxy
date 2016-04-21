import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableAutoConfiguration
@Configuration
@ComponentScan({"service", "config", "control", "tasks"})
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class Application {
    final static Logger logger = LoggerFactory.getLogger("Application");

    public static void main(String[] args) throws Throwable {
        final ApplicationContext ctx = SpringApplication.run(Application.class, args);

        logger.debug("Let's inspect the beans provided by Spring Boot:");
        final String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (final String beanName : beanNames) {
            logger.debug(beanName);
        }
    }

    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Datasource-API")
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/api/v1/entities/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Organicity Datasource API")
                .description("Organicity Datasource API")
                .termsOfServiceUrl("http://www.organicity.eu")
                .contact("Organicity Helpdesk")
                .version("0.1").build();
    }


}
