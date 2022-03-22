package ratepay.bugtracker.usecase.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.service.exception.NoDataFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class IssueUpdateUseCaseTest {

    @InjectMocks
    private IssueUpdateUseCase useCase;

    @Mock
    private IssueUpdateOutputBoundary outputBoundary;

    @Mock
    private IssueJpaGateway jpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldThrowException() {
        IssueUpdateRequest request = getIssueUpdateRequest();
        Long issueId = request.getIssueId();
        when(jpaGateway.findById(issueId)).thenReturn(null);
        assertThatThrownBy(() -> useCase.update(request, outputBoundary))
                .hasMessageContaining("No data found with issue id {} " + issueId)
                .isInstanceOf(NoDataFoundException.class);
    }

    @Test
    void itShouldUpdate() throws NoDataFoundException {
        IssueUpdateRequest request = getIssueUpdateRequest();
        when(jpaGateway.findById(request.getIssueId())).thenReturn(Optional.of(prepareIssue()));

        useCase.update(request, outputBoundary);

        ArgumentCaptor<IssueUpdateResponse> captor = ArgumentCaptor.forClass(IssueUpdateResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());
        verify(jpaGateway, times(1)).save(any());
        IssueUpdateResponse actual = captor.getValue();

        assertThat(actual.isSuccess()).isEqualTo(true);
    }

    private Issue prepareIssue() {
        Issue issue = new Issue();
        issue.setIssueId(1L);
        issue.setIssueType("BUG");
        issue.setDescription("Transfer bugs");
        issue.setSummary("Fix dev bugs");
        return issue;
    }

    private IssueUpdateRequest getIssueUpdateRequest() {
        IssueUpdateRequest request = new IssueUpdateRequest();
        request.setIssueId(1L);
        request.setIssueType("BUG");
        request.setSummary("Fix prod bugs");
        request.setDescription("Payrol bugs");
        return request;
    }
}