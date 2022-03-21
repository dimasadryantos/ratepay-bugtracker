package ratepay.bugtracker.config;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ratepay.bugtracker.service.config.PersistenceConfig;
import ratepay.bugtracker.service.config.ServiceConfig;

@DataJpaTest //disable full-auto configuration apply only relevant to JPA test
@Import({PersistenceConfig.class, ServiceConfig.class})
@WebMvcTest //disable full-auto configuration apply only relevant to mvc test & auto configure mvc test instance
@ComponentScan(basePackages = "ratepay.bugtracker.service")
public class ApplicationTestConfiguration {
}
