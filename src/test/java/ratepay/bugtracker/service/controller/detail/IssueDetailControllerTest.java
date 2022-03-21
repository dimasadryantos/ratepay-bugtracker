package ratepay.bugtracker.service.controller.detail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ratepay.bugtracker.client.detail.response.IssueDetailHttpResponse;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.service.exception.NoDataFoundException;
import ratepay.bugtracker.usecase.detail.IssueDetailInputBoundary;
import ratepay.bugtracker.usecase.detail.IssueDetailOutputBoundary;
import ratepay.bugtracker.usecase.detail.IssueDetailResponse;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

class IssueDetailControllerTest {

    @InjectMocks
    private IssueDetailController controller;

    @Mock
    private IssueDetailInputBoundary useCase;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldReturnCorrectResponse() throws NoDataFoundException {
        Answer<Void> response = this::getPresenterResponse;
        doAnswer(response).when(useCase).detail(any());

        IssueDetailHttpResponse detail = controller.detail();

        assertDetailValues(detail);
    }

    private void assertDetailValues(IssueDetailHttpResponse detail) {
        assertThat(detail.getIssues()).isNotEmpty();
        detail.getIssues().forEach(issue -> {
            assertThat(issue.getIssueType()).isIn("BUG", "TASK");
            assertThat(issue.getSummary()).isNotEmpty();
            assertThat(issue.getDescription()).isNotEmpty();
        });
    }


    private Void getPresenterResponse(InvocationOnMock invocationOnMock) {
        IssueDetailOutputBoundary presenter = invocationOnMock.getArgument(0);
        IssueDetailResponse response = new IssueDetailResponse();
        response.setIssues(constructIssue());
        presenter.present(response);
        return null;
    }


    private List<Issue> constructIssue() {
        return List.of(
                Issue.builder(1L).setIssueType("BUG").setSummary("Prod bug").setDescription("Fix bug on module").build(),
                Issue.builder(2L).setIssueType("TASK").setSummary("Create module").setDescription("Create new feature").build(),
                Issue.builder(2L).setIssueType("BUG").setSummary("Dev bug").setDescription("Fix bug on module").build());
    }
}