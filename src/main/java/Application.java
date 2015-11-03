import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@EnableAutoConfiguration
@Configuration
@ComponentScan({"service","config", "control", "tasks"})
@SpringBootApplication
@EnableScheduling
public class Application {
    final static Logger logger = LoggerFactory.getLogger("Application");

    public static void main(String[] args) throws Throwable {
        final ApplicationContext ctx =SpringApplication.run(Application.class, args);

        logger.debug("Let's inspect the beans provided by Spring Boot:");
        final String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (final String beanName : beanNames) {
            logger.debug(beanName);
        }
    }

}
