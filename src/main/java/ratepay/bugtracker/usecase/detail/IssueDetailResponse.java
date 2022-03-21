package ratepay.bugtracker.usecase.detail;

import ratepay.bugtracker.persistence.issue.Issue;

import java.util.List;

public class IssueDetailResponse {

    private List<Issue> issues;


    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }
}
