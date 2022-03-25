package ratepay.bugtracker.usecase.get;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IssueSpecificUseCaseTest {


    @InjectMocks
    private IssueSpecificUseCase useCase;

    @Mock
    private IssueJpaGateway gateway;

    @Mock
    private IssueSpecificOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldGetSpecificTicket() throws NoDataFoundException {
        IssueSpecificRequest request = new IssueSpecificRequest();
        request.setIssueId(1L);

        when(gateway.findById(any())).thenReturn(Optional.of(constructIssue()));
        useCase.getSpecificIssue(request, outputBoundary);

        ArgumentCaptor<IssueSpecificResponse> captor = ArgumentCaptor.forClass(IssueSpecificResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());
        IssueSpecificResponse actual = captor.getValue();

        assertThat(actual).isNotNull();
        Issue issue = actual.getIssue();
        assertThat(issue.getIssueId()).isEqualTo(request.getIssueId());
        assertThat(issue.getIssueType()).isEqualTo("BUG");
        assertThat(issue.getSummary()).isEqualTo("Bug fix");
        assertThat(issue.getDescription()).isEqualTo("please fix this");
    }


    private Issue constructIssue() {
        return Issue.builder().issueId(1L)
                .issueType("BUG").summary("Bug fix")
                .description("please fix this").build();
    }
}