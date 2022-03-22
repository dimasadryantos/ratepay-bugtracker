package ratepay.bugtracker.service.controller.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratepay.bugtracker.client.update.request.IssueUpdateHttpRequest;
import ratepay.bugtracker.service.exception.NoDataFoundException;
import ratepay.bugtracker.usecase.update.IssueUpdateInputBoundary;
import ratepay.bugtracker.usecase.update.IssueUpdateRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IssueUpdateControllerTest {

    @InjectMocks
    private IssueUpdateController controller;

    @Mock
    private IssueUpdateInputBoundary useCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldSendCorrectRequestParam() throws NoDataFoundException {
        IssueUpdateHttpRequest httpRequest = getHttpRequest();

        controller.update(httpRequest);

        ArgumentCaptor<IssueUpdateRequest> captor = ArgumentCaptor.forClass(IssueUpdateRequest.class);
        verify(useCase, times(1)).update(captor.capture(), any());
        IssueUpdateRequest actual = captor.getValue();

        assertThat(actual.getIssueId()).isEqualTo(httpRequest.getIssueId());
        assertThat(actual.getSummary()).isEqualTo(httpRequest.getSummary());
        assertThat(actual.getDescription()).isEqualTo(httpRequest.getDescription());
        assertThat(actual.getIssueType()).isEqualTo(httpRequest.getIssueType());
    }

    private IssueUpdateHttpRequest getHttpRequest() {
        IssueUpdateHttpRequest request = new IssueUpdateHttpRequest();
        request.setIssueType("BUG");
        request.setIssueId(1L);
        request.setSummary("Fix prod bugs");
        request.setDescription("Payroll bugs");
        return request;
    }


}