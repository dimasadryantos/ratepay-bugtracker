package ratepay.bugtracker.usecase.detail;

import ratepay.bugtracker.service.exception.NoDataFoundException;

public interface IssueDetailInputBoundary {

    void detail(IssueDetailOutputBoundary outputBoundary) throws NoDataFoundException;
}
