package ratepay.bugtracker.usecase.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class IssueCreationUseCaseTest {

    @InjectMocks
    private IssueCreationUseCase useCase;

    @Mock
    private IssueCreationOutputBoundary outputBoundary;

    @Mock
    private IssueJpaGateway issueJpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldThrowException() {
        IssueCreationRequest request = getRequest();
        doThrow(IllegalStateException.class).when(issueJpaGateway).save(any());
        assertThatThrownBy(() -> useCase.create(request, outputBoundary)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void itShouldReturnCorrectResponse() {
        IssueCreationRequest request = getRequest();

        useCase.create(request, outputBoundary);

        ArgumentCaptor<IssueCreationResponse> captor = ArgumentCaptor.forClass(IssueCreationResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());
        IssueCreationResponse actual = captor.getValue();

        assertThat(actual.isSuccess()).isTrue();
    }


    @Test
    void itShouldSaveCorrectIssue() {
        IssueCreationRequest request = getRequest();

        useCase.create(request, outputBoundary);

        ArgumentCaptor<Issue> captor = ArgumentCaptor.forClass(Issue.class);
        verify(issueJpaGateway, times(1)).save(captor.capture());
        Issue actual = captor.getValue();

        assertThat(actual.getIssueType()).isEqualTo("BUG");
        assertThat(actual.getSummary()).isEqualTo("Fix production bugs");
        assertThat(actual.getDescription()).isEqualTo("Please fix module x");
    }


    private IssueCreationRequest getRequest() {
        IssueCreationRequest request = new IssueCreationRequest();
        request.setIssueType("BUG");
        request.setDescription("Please fix module x");
        request.setSummary("Fix production bugs");
        return request;
    }
}