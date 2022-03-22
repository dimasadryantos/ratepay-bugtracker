package ratepay.bugtracker.usecase.update;

import ratepay.bugtracker.service.exception.NoDataFoundException;

public interface IssueUpdateInputBoundary {
    void update(IssueUpdateRequest request, IssueUpdateOutputBoundary outputBoundary) throws NoDataFoundException;

}
