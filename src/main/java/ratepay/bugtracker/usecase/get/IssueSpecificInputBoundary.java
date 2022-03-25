package ratepay.bugtracker.usecase.get;

import ratepay.bugtracker.service.exception.NoDataFoundException;

public interface IssueSpecificInputBoundary {

    void getSpecificIssue(IssueSpecificRequest response, IssueSpecificOutputBoundary outputBoundary) throws NoDataFoundException;
}
