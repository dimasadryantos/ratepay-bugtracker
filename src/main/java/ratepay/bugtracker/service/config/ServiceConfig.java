package ratepay.bugtracker.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.usecase.create.IssueCreationInputBoundary;
import ratepay.bugtracker.usecase.create.IssueCreationUseCase;
import ratepay.bugtracker.usecase.detail.IssueDetailInputBoundary;
import ratepay.bugtracker.usecase.detail.IssueDetailUseCase;
import ratepay.bugtracker.usecase.update.IssueUpdateInputBoundary;
import ratepay.bugtracker.usecase.update.IssueUpdateUseCase;

@Configuration
public class ServiceConfig {

    @Bean
    public IssueCreationInputBoundary creationUseCase(IssueJpaGateway issueJpaGateway) {
        return new IssueCreationUseCase(issueJpaGateway);
    }

    @Bean
    public IssueDetailInputBoundary detailUseCase(IssueJpaGateway issueJpaGateway) {
        return new IssueDetailUseCase(issueJpaGateway);
    }

    @Bean
    public IssueUpdateInputBoundary updateUseCase(IssueJpaGateway issueJpaGateway) {
        return new IssueUpdateUseCase(issueJpaGateway);
    }


}
