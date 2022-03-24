package ratepay.bugtracker.usecase.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueCreationRequest {

    private String issueType;

    private String summary;

    private String description;

}
