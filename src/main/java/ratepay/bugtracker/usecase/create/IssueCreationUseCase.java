package ratepay.bugtracker.usecase.create;

import lombok.RequiredArgsConstructor;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;

@RequiredArgsConstructor
public class IssueCreationUseCase implements IssueCreationInputBoundary {

    private final IssueJpaGateway issueJpaGateway;


    @Override
    public void create(IssueCreationRequest request, IssueCreationOutputBoundary outputBoundary) {
        boolean saveIssue = doSaveIssue(request);
        doConstructResponse(outputBoundary, saveIssue);
    }

    private void doConstructResponse(IssueCreationOutputBoundary outputBoundary, boolean saveIssue) {
        IssueCreationResponse response = new IssueCreationResponse();
        response.setSuccess(saveIssue);
        outputBoundary.present(response);
    }

    private boolean doSaveIssue(IssueCreationRequest request) {
        try {
            Issue issue = Issue.buildCreation(request);
            issueJpaGateway.save(issue);
        } catch (Exception exception) {
            throw new IllegalStateException("Error when save issue");
        }
        return true;
    }


}
