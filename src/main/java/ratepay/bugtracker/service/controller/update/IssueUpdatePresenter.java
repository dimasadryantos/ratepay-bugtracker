package ratepay.bugtracker.service.controller.update;

import ratepay.bugtracker.client.update.response.IssueUpdateHttpResponse;
import ratepay.bugtracker.usecase.update.IssueUpdateOutputBoundary;
import ratepay.bugtracker.usecase.update.IssueUpdateResponse;

public class IssueUpdatePresenter implements IssueUpdateOutputBoundary {


    private IssueUpdateHttpResponse httpResponse;


    @Override
    public void present(IssueUpdateResponse response) {
        httpResponse = new IssueUpdateHttpResponse();
        httpResponse.setSuccess(response.isSuccess());
    }

    public IssueUpdateHttpResponse getHttpResponse() {
        return httpResponse;
    }
}
