package ratepay.bugtracker.client.detail.response;

import lombok.Getter;
import lombok.Setter;
import ratepay.bugtracker.persistence.issue.Issue;

import java.util.List;

@Getter
@Setter
public class IssueDetailHttpResponse {

    private List<Issue> issues;

}
