package ratepay.bugtracker.client.update.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueUpdateHttpRequest {

    private Long issueId;

    private String issueType;

    private String summary;

    private String description;

}
