package ratepay.bugtracker.service.controller.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ratepay.bugtracker.client.detail.response.IssueDetailHttpResponse;
import ratepay.bugtracker.service.exception.NoDataFoundException;
import ratepay.bugtracker.usecase.detail.IssueDetailInputBoundary;

@RestController
@RequiredArgsConstructor
public class IssueDetailController {

    private final IssueDetailInputBoundary useCase;

    @GetMapping(value = "/v1/issues")
    @ResponseStatus(HttpStatus.OK)
    public IssueDetailHttpResponse detail() throws NoDataFoundException {
        IssueDetailPresenter presenter = new IssueDetailPresenter();
        useCase.detail(presenter);
        return presenter.getDetail();
    }

}
