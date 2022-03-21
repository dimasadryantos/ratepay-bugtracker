package ratepay.bugtracker.persistence.issue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static ratepay.bugtracker.utils.TestConstant.DESCRIPTION;
import static ratepay.bugtracker.utils.TestConstant.ISSUE_TYPE;
import static ratepay.bugtracker.utils.TestConstant.SUMMARY;

class IssueJpaGatewayImplTest {

    @InjectMocks
    private IssueJpaGatewayImpl issueJpaGateway;

    @Mock
    private IssueRepository issueRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void itShouldSaveIssue() {
        Issue issue = Issue.builder(1L)
                .setIssueType(ISSUE_TYPE)
                .setSummary(SUMMARY)
                .setDescription(DESCRIPTION).build();

        issueJpaGateway.save(issue);

        ArgumentCaptor<Issue> captor = ArgumentCaptor.forClass(Issue.class);
        verify(issueRepository, times(1)).save(captor.capture());

        Issue actual = captor.getValue();

        assertThat(actual.getIssueId()).isEqualTo(1L);
        assertThat(actual.getIssueType()).isEqualTo(ISSUE_TYPE);
        assertThat(actual.getSummary()).isEqualTo(SUMMARY);
        assertThat(actual.getDescription()).isEqualTo(DESCRIPTION);
    }


}