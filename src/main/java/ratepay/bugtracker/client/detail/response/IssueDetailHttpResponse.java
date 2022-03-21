package ratepay.bugtracker.client.detail.response;

import ratepay.bugtracker.persistence.issue.Issue;

import java.util.List;

public class IssueDetailHttpResponse {

    private List<Issue> issues;


    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
