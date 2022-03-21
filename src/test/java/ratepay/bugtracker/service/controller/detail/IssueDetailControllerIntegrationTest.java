package ratepay.bugtracker.service.controller.detail;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ratepay.bugtracker.client.detail.response.IssueDetailHttpResponse;
import ratepay.bugtracker.config.ApplicationTestConfiguration;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationTestConfiguration.class)
@AutoConfigureMockMvc
class IssueDetailControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IssueRepository repository;

    private static ObjectMapper mapper = null;


    @Test
    void itShouldCorrectDetails() throws Exception {
        doSaveIssue();

        MvcResult mvcResult = getMvcResult();



        String contentAsString = mvcResult.getResponse().getContentAsString();
        IssueDetailHttpResponse issueDetailHttpResponse = getMapper().readValue(contentAsString, IssueDetailHttpResponse.class);
        List<Issue> issues = issueDetailHttpResponse.getIssues();
        assertThat(issues).isNotEmpty();
    }

    private void doSaveIssue() {
        Issue issue = Issue.builder(1L).setIssueType("TASK").setSummary("Create new module").setDescription("Create new module payroll").build();
        Issue issue2 = Issue.builder(2L).setIssueType("BUG").setSummary("Fix prod issue").setDescription("Fix bug on module payroll").build();
        repository.save(issue);
        repository.save(issue2);
    }

    private MvcResult getMvcResult() throws Exception {
        return mockMvc.perform(get("/v1/issues")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
    }

    public static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper;
        }
        return mapper;
    }

}