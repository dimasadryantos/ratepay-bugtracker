package ratepay.bugtracker.persistence.issue;

import java.util.List;
import java.util.Optional;

public interface IssueJpaGateway {
    void save(Issue issue);

    List<Issue> getIssues();

    Issue updateIssue(Long issueId);

    Optional<Issue> findById(Long issueId);
}
