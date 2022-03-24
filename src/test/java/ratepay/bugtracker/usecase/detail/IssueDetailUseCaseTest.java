package ratepay.bugtracker.usecase.detail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueJpaGateway;
import ratepay.bugtracker.service.exception.NoDataFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IssueDetailUseCaseTest {


    @InjectMocks
    private IssueDetailUseCase useCase;

    @Mock
    private IssueDetailOutputBoundary outputBoundary;


    @Mock
    private IssueJpaGateway jpaGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void itShouldThrowException() {
        when(jpaGateway.getIssues()).thenReturn(null);
        assertThatThrownBy(() -> useCase.detail(outputBoundary))
                .hasMessageContaining("Details are empty , please check again the input data")
                .isInstanceOf(NoDataFoundException.class);
    }

    @Test
    void itShouldReturnCorrectDetail() throws NoDataFoundException {
        when(jpaGateway.getIssues()).thenReturn(List.of(
                Issue.builder().issueId(1L).issueType("BUG").summary("Prod bug").description("Fix bug on module").build(),
                Issue.builder().issueId(2L).issueType("TASK").summary("Create module").description("Create new feature").build(),
                Issue.builder().issueId(3L).issueType("BUG").summary("Dev bug").description("Fix bug on module").build()));

        useCase.detail(outputBoundary);

        ArgumentCaptor<IssueDetailResponse> captor = ArgumentCaptor.forClass(IssueDetailResponse.class);
        verify(outputBoundary, times(1)).present(captor.capture());
        IssueDetailResponse actual = captor.getValue();

        assertThat(actual.getIssues()).isNotEmpty();
        actual.getIssues().forEach(issue -> {
            assertThat(issue.getIssueType()).isIn("BUG", "TASK");
            assertThat(issue.getSummary()).isNotEmpty();
            assertThat(issue.getDescription()).isNotEmpty();
        });
    }
}