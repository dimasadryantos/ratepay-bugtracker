package ratepay.bugtracker.persistence.issue;

import java.util.List;
import java.util.Optional;

public class IssueJpaGatewayImpl implements IssueJpaGateway {

    private final IssueRepository issueRepository;


    public IssueJpaGatewayImpl(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Override
    public void save(Issue issue) {
        issueRepository.save(issue);
    }

    @Override
    public List<Issue> getIssues() {
        return issueRepository.findAll();
    }

    @Override
    public Issue updateIssue(Long issueId) {
        return null;
    }

    @Override
    public Optional<Issue> findById(Long issueId) {
        return issueRepository.findById(issueId);
    }


}
