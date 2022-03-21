package ratepay.bugtracker.usecase.create;

import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;

public class IssueCreationUseCase implements IssueCreationInputBoundary {

    private final IssueJpaGateway issueJpaGateway;

    public IssueCreationUseCase(IssueJpaGateway issueJpaGateway) {
        this.issueJpaGateway = issueJpaGateway;
    }

    @Override
    public void create(IssueCreationRequest request, IssueCreationOutputBoundary outputBoundary) {
        boolean saveIssue = doSaveIssue(request);
        doConstructResponse(outputBoundary, saveIssue);
    }

    private void doConstructResponse(IssueCreationOutputBoundary outputBoundary, boolean saveIssue) {
        outputBoundary.present(new IssueCreationResponse(saveIssue));
    }

    private boolean doSaveIssue(IssueCreationRequest request) {
        try {
            Issue issue = new Issue();
            issue.setDescription(request.getDescription());
            issue.setIssueType(request.getIssueType());
            issue.setSummary(request.getSummary());
            issueJpaGateway.save(issue);
        } catch (Exception exception) {
            throw new IllegalStateException("Error when save issue");
        }
        return true;
    }


}
