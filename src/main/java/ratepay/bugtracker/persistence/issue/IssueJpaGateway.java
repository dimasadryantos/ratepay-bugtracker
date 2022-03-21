package ratepay.bugtracker.persistence.issue;

import java.util.List;

public interface IssueJpaGateway {
    void save(Issue issue);

    List<Issue> getIssues();

    Issue updateIssue(Long issueId);
}
