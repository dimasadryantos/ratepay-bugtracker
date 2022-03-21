package ratepay.bugtracker.usecase.update;

public interface IssueUpdateInputBoundary {
    void update(IssueUpdateRequest request,IssueUpdateOutputBoundary outputBoundary);

}
