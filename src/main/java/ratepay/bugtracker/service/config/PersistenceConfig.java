package ratepay.bugtracker.service.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ratepay.bugtracker.persistence.issue.IssueRepository;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.persistence.issue.IssueJpaGatewayImpl;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "ratepay.bugtracker.persistence")
@EntityScan(basePackages = "ratepay.bugtracker.persistence")
public class PersistenceConfig {

    @Bean
    public IssueJpaGateway issueJpaGateway(IssueRepository repository) {
        return new IssueJpaGatewayImpl(repository);
    }


}
