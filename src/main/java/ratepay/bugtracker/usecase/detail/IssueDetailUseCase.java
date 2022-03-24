package ratepay.bugtracker.usecase.detail;

import lombok.RequiredArgsConstructor;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.service.exception.NoDataFoundException;

import java.util.List;

@RequiredArgsConstructor
public class IssueDetailUseCase implements IssueDetailInputBoundary {

    private final IssueJpaGateway jpaGateway;


    @Override
    public void detail(IssueDetailOutputBoundary outputBoundary) throws NoDataFoundException {
        List<Issue> issueDetails = getIssueDetail();
        outputBoundary.present(constructResponse(issueDetails));
    }


    private IssueDetailResponse constructResponse(List<Issue> issueDetails) {
        IssueDetailResponse response = new IssueDetailResponse();
        response.setIssues(issueDetails);
        return response;
    }


    private List<Issue> getIssueDetail() throws NoDataFoundException {
        List<Issue> issues = jpaGateway.getIssues();
        if (null == issues || issues.isEmpty()) {
            throw new NoDataFoundException("Details are empty , please check again the input data");
        }
        return issues;
    }
}
