package ratepay.bugtracker.client.create.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueCreationHttpRequest {

    private String issueType;

    private String summary;

    private String description;

}
