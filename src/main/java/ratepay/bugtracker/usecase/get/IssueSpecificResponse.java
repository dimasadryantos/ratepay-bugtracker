package ratepay.bugtracker.usecase.get;

import lombok.Getter;
import lombok.Setter;
import ratepay.bugtracker.persistence.issue.Issue;

@Getter
@Setter
public class IssueSpecificResponse {
    private Issue issue;
}
