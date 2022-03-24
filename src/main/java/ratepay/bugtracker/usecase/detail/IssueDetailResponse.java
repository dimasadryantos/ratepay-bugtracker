package ratepay.bugtracker.usecase.detail;

import lombok.Getter;
import lombok.Setter;
import ratepay.bugtracker.persistence.issue.Issue;

import java.util.List;

@Getter
@Setter
public class IssueDetailResponse {

    private List<Issue> issues;

}
