package ratepay.bugtracker.service.controller.update;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ratepay.bugtracker.client.update.request.IssueUpdateHttpRequest;
import ratepay.bugtracker.client.update.response.IssueUpdateHttpResponse;
import ratepay.bugtracker.service.exception.NoDataFoundException;
import ratepay.bugtracker.usecase.update.IssueUpdateInputBoundary;
import ratepay.bugtracker.usecase.update.IssueUpdateRequest;

@RestController
@RequiredArgsConstructor
public class IssueUpdateController {

    private final IssueUpdateInputBoundary useCase;


    @PutMapping(value = "/v1/issues")
    @ResponseStatus(HttpStatus.OK)
    public IssueUpdateHttpResponse update(@RequestBody IssueUpdateHttpRequest httpRequest) throws NoDataFoundException {
        IssueUpdateRequest request = getRequest(httpRequest);
        IssueUpdatePresenter presenter = new IssueUpdatePresenter();
        useCase.update(request, presenter);
        return presenter.getHttpResponse();
    }

    private IssueUpdateRequest getRequest(IssueUpdateHttpRequest httpRequest) {
        IssueUpdateRequest request = new IssueUpdateRequest();
        request.setIssueId(httpRequest.getIssueId());
        request.setIssueType(httpRequest.getIssueType());
        request.setSummary(httpRequest.getSummary());
        request.setDescription(httpRequest.getDescription());
        return request;
    }
}
