package ratepay.bugtracker.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static ratepay.bugtracker.utils.TestConstant.DESCRIPTION;
import static ratepay.bugtracker.utils.TestConstant.ISSUE_TYPE;
import static ratepay.bugtracker.utils.TestConstant.SUMMARY;

@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Test
    void itShouldSaveIssue() {
        Issue issue = Issue.builder(1L)
                .setIssueType(ISSUE_TYPE)
                .setSummary(SUMMARY)
                .setDescription(DESCRIPTION).build();

        issueRepository.save(issue);

        Optional<Issue> issueById = issueRepository.findById(1L);

        issueById.ifPresent(detail -> {
            assertThat(detail.getIssueId()).isEqualTo(1L);
            assertThat(detail.getIssueType()).isEqualTo(ISSUE_TYPE);
            assertThat(detail.getSummary()).isEqualTo(SUMMARY);
            assertThat(detail.getDescription()).isEqualTo(DESCRIPTION);
        });
    }
}