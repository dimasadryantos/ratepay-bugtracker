package ratepay.bugtracker.usecase.create;

public interface IssueCreationInputBoundary {

    void create(IssueCreationRequest request, IssueCreationOutputBoundary outputBoundary);
}
