package ratepay.bugtracker.usecase.get;

import lombok.RequiredArgsConstructor;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.service.exception.NoDataFoundException;

@RequiredArgsConstructor
public class IssueSpecificUseCase implements IssueSpecificInputBoundary {

    private final IssueJpaGateway gateway;

    @Override
    public void getSpecificIssue(IssueSpecificRequest request, IssueSpecificOutputBoundary outputBoundary) throws NoDataFoundException {
        Issue issue = getIssueById(request);
        constructResponse(outputBoundary, issue);
    }

    private Issue getIssueById(IssueSpecificRequest request) throws NoDataFoundException {
        return gateway.findById(request.getIssueId()).orElseThrow(() -> new NoDataFoundException("No data available"));
    }

    private void constructResponse(IssueSpecificOutputBoundary outputBoundary, Issue issue) {
        IssueSpecificResponse response = new IssueSpecificResponse();
        response.setIssue(issue);
        outputBoundary.present(response);
    }
}
