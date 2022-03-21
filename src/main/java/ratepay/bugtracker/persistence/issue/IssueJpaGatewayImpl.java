package ratepay.bugtracker.persistence.issue;

import ratepay.bugtracker.service.exception.NoDataFoundException;

import java.util.List;

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


}
