package ratepay.bugtracker.usecase.create;

public class IssueCreationResponse {
    private final boolean success;

    public IssueCreationResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
