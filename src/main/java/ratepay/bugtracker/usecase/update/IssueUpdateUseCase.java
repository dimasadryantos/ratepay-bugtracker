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
        Issue issue = doGetIssueById(issueId);
        issue.setIssueType(request.getIssueType());
        issue.setDescription(request.getDescription());
        issue.setSummary(request.getSummary());
        jpaGateway.save(issue);
    }

    private Issue doGetIssueById(Long issueId) throws NoDataFoundException {
        Optional<Issue> issueById = jpaGateway.findById(issueId);
        if (null == issueById || issueById.isEmpty()) {
            throw new NoDataFoundException("No data found with issue id {} " + issueId);
        }
        return issueById.get();
    }
}
