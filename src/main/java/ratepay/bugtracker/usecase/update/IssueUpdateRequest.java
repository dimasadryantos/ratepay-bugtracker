package ratepay.bugtracker.usecase.update;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueUpdateRequest {

    private Long issueId;

    private String issueType;

    private String summary;

    private String description;

}
