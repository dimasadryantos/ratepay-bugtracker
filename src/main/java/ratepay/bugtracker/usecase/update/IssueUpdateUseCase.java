package ratepay.bugtracker.usecase.update;

import lombok.RequiredArgsConstructor;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.service.exception.NoDataFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class IssueUpdateUseCase implements IssueUpdateInputBoundary {

    private final IssueJpaGateway jpaGateway;

    @Override
    public void update(IssueUpdateRequest request, IssueUpdateOutputBoundary outputBoundary) throws NoDataFoundException {
        updateIssue(request);
        constructResponse(outputBoundary);
    }

    private void constructResponse(IssueUpdateOutputBoundary outputBoundary) {
        IssueUpdateResponse response = new IssueUpdateResponse();
        response.setSuccess(Boolean.TRUE);
        outputBoundary.present(response);
    }

    private void updateIssue(IssueUpdateRequest request) throws NoDataFoundException {
        Long issueId = request.getIssueId();
        Optional<Issue> issue = doGetIssueById(issueId);
        Issue newIssue = doMapNewIssue(request, issue);
        jpaGateway.save(newIssue);
    }

    private Issue doMapNewIssue(IssueUpdateRequest request, Optional<Issue> issue) {
        return issue.map(ticket -> Issue.builder()
                .issueType(request.getIssueType()).summary(request.getSummary()).description(request.getDescription()).build())
                .orElse(null);
    }

    private Optional<Issue> doGetIssueById(Long issueId) throws NoDataFoundException {
        return Optional.of(jpaGateway.findById(issueId)
                .orElseThrow(() -> new NoDataFoundException("No data found with issue id {} " + issueId)));
    }
}
