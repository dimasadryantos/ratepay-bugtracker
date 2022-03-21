package ratepay.bugtracker.service.controller.create;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ratepay.bugtracker.client.create.request.IssueCreationHttpRequest;
import ratepay.bugtracker.config.ApplicationTestConfiguration;

import static org.assertj.core.api.Assertions.fail;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ApplicationTestConfiguration.class)
@AutoConfigureMockMvc
class IssueCreationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void itShouldCreateNewIssue() throws Exception {
        IssueCreationHttpRequest httpRequest = getIssueCreationHttpRequest();
        String body = objectToJson(httpRequest);
        ResultActions resultActions = postOrder(body);

        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.success",is(true)));
    }

    private IssueCreationHttpRequest getIssueCreationHttpRequest() {
        IssueCreationHttpRequest httpRequest =
                new IssueCreationHttpRequest();
        httpRequest.setIssueType("BUG");
        httpRequest.setSummary("Prod issue");
        httpRequest.setDescription("Please fix this module");
        return httpRequest;
    }


    private ResultActions postOrder(String body) throws Exception {
        return mockMvc.perform(post("/v1/issues")
                .content(body)
                .contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    private String objectToJson(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            fail("Failed to convert object to json");
            return null;
        }
    }
}