package ratepay.bugtracker.service.controller.create;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ratepay.bugtracker.client.create.request.IssueCreationHttpRequest;
import ratepay.bugtracker.client.create.response.IssueCreationHttpResponse;
import ratepay.bugtracker.service.exception.BadRequestException;
import ratepay.bugtracker.usecase.create.IssueCreationInputBoundary;
import ratepay.bugtracker.usecase.create.IssueCreationRequest;

@RestController
public class IssueCreationController {

    private final IssueCreationInputBoundary useCase;

    public IssueCreationController(IssueCreationInputBoundary useCase) {
        this.useCase = useCase;
    }

    @PostMapping(value = "/v1/issues")
    @ResponseStatus(HttpStatus.CREATED)
    public IssueCreationHttpResponse create(@RequestBody IssueCreationHttpRequest httpRequest) throws BadRequestException {
        try {
            IssueCreationRequest request = getRequest(httpRequest);
            IssueCreationPresenter presenter = new IssueCreationPresenter();
            useCase.create(request, presenter);
            return presenter.getHttpResponse();
        } catch (Exception exception) {
            throw new BadRequestException("Invalid request , Please input correct request");
        }
    }

    private IssueCreationRequest getRequest(IssueCreationHttpRequest httpRequest) {
        IssueCreationRequest request = new IssueCreationRequest();
        request.setIssueType(httpRequest.getIssueType());
        request.setDescription(httpRequest.getDescription());
        request.setSummary(httpRequest.getSummary());
        return request;
    }
}
