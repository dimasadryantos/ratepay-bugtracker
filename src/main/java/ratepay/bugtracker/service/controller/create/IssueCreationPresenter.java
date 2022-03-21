package ratepay.bugtracker.service.controller.create;

import ratepay.bugtracker.client.create.response.IssueCreationHttpResponse;
import ratepay.bugtracker.usecase.create.IssueCreationOutputBoundary;
import ratepay.bugtracker.usecase.create.IssueCreationResponse;

public class IssueCreationPresenter implements IssueCreationOutputBoundary {

    private IssueCreationHttpResponse httpResponse;


    @Override
    public void present(IssueCreationResponse response) {
        httpResponse = new IssueCreationHttpResponse();
        httpResponse.setSuccess(response.isSuccess());
    }

    public IssueCreationHttpResponse getHttpResponse() {
        return httpResponse;
    }
}
