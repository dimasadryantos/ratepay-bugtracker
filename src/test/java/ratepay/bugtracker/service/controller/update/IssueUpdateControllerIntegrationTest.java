package ratepay.bugtracker.service.controller.update;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ratepay.bugtracker.client.update.request.IssueUpdateHttpRequest;
import ratepay.bugtracker.config.ApplicationTestConfiguration;
import ratepay.bugtracker.persistence.issue.Issue;
import ratepay.bugtracker.persistence.issue.IssueRepository;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = ApplicationTestConfiguration.class)
@AutoConfigureMockMvc
class IssueUpdateControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IssueRepository repository;


    @Test
    void itShouldUpdate() throws Exception {
        doSaveIssue();
        IssueUpdateHttpRequest httpRequest = getHttpRequest();
        ResultActions resultActions = buildHttpRequest(objectToJson(httpRequest));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.success", is(true)));
    }

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }


    private IssueUpdateHttpRequest getHttpRequest() {
        IssueUpdateHttpRequest request = new IssueUpdateHttpRequest();
        request.setIssueType("BUG");
        request.setIssueId(1L);
        request.setSummary("Fix prod bugs");
        request.setDescription("Payroll bugs");
        return request;
    }

    private ResultActions buildHttpRequest(String body) throws Exception {
        return mockMvc.perform(put("/v1/issues").content(body).contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    private void doSaveIssue() {
        Issue issue = new Issue();
        issue.setIssueId(1L);
        issue.setIssueType("BUG");
        issue.setSummary("Prod issue");
        issue.setDescription("Fix payroll issue");
        repository.save(issue);
    }

}