package ratepay.bugtracker.service.controller.create;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratepay.bugtracker.client.create.request.IssueCreationHttpRequest;
import ratepay.bugtracker.service.exception.BadRequestException;
import ratepay.bugtracker.usecase.create.IssueCreationInputBoundary;
import ratepay.bugtracker.usecase.create.IssueCreationRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IssueCreationControllerTest {

    @InjectMocks
    private IssueCreationController issueCreationController;

    @Mock
    private IssueCreationInputBoundary useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldThrowExceptions() {
        IssueCreationHttpRequest httpRequest = getIssueCreationHttpRequest();
        doThrow(new RuntimeException()).when(useCase).create(any(), any());
        assertThatThrownBy(() -> issueCreationController.create(httpRequest))
                .isInstanceOf(BadRequestException.class).hasMessageContaining("Invalid request , Please input correct request");
    }

    private IssueCreationHttpRequest getIssueCreationHttpRequest() {
        IssueCreationHttpRequest httpRequest =
                new IssueCreationHttpRequest();
        httpRequest.setIssueType("BUG");
        httpRequest.setSummary("Prod issue");
        httpRequest.setDescription("Please fix this module");
        return httpRequest;
    }

    @Test
    void itShouldSendCorrectRequest() throws BadRequestException {
        IssueCreationHttpRequest httpRequest =
                getIssueCreationHttpRequest();

        issueCreationController.create(httpRequest);

        ArgumentCaptor<IssueCreationRequest> captor = ArgumentCaptor.forClass(IssueCreationRequest.class);
        verify(useCase, times(1)).create(captor.capture(), any());

        IssueCreationRequest actual = captor.getValue();
        assertThat(actual.getIssueType()).isEqualTo("BUG");
        assertThat(actual.getSummary()).isEqualTo("Prod issue");
        assertThat(actual.getDescription()).isEqualTo("Please fix this module");
    }
}