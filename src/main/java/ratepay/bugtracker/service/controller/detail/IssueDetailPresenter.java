package ratepay.bugtracker.service.controller.detail;

import ratepay.bugtracker.client.detail.response.IssueDetailHttpResponse;
import ratepay.bugtracker.usecase.detail.IssueDetailOutputBoundary;
import ratepay.bugtracker.usecase.detail.IssueDetailResponse;

public class IssueDetailPresenter implements IssueDetailOutputBoundary {

    private IssueDetailHttpResponse httpResponse;


    @Override
    public void present(IssueDetailResponse response) {
        httpResponse = new IssueDetailHttpResponse();
        httpResponse.setIssues(response.getIssues());
    }


    public IssueDetailHttpResponse getDetail() {
        return httpResponse;
    }
}
