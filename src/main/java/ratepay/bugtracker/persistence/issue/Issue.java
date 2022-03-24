package ratepay.bugtracker.persistence.issue;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ratepay.bugtracker.persistence.AuditTrail;
import ratepay.bugtracker.usecase.create.IssueCreationRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Builder
@Data
@Table(name = "issue")
public class Issue extends AuditTrail implements Serializable {

    private static final long serialVersionUID = 8325966390079928911L;

    @Id
    @SequenceGenerator(name = "issue_sequence", sequenceName = "issueSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "issue_sequence")
    private Long issueId;

    @Column(name = "issue_type")
    private String issueType;

    @Column(name = "summary")
    private String summary;

    @Column(name = "description")
    private String description;

    public Issue(Long issueId, String issueType, String summary, String description) {
        this.issueId = issueId;
        this.issueType = issueType;
        this.summary = summary;
        this.description = description;
    }

    public static Issue buildCreation(IssueCreationRequest request) {
        return Issue.builder().issueType(request.getIssueType())
                .summary(request.getSummary())
                .description(request.getDescription()).build();
    }
}
