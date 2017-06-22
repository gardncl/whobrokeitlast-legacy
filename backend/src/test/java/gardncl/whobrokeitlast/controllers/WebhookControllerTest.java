package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.services.TravisParseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class WebhookControllerTest {

    @Spy
    @InjectMocks
    private WebhookController webhookController;

    @Mock
    private TravisParseService travisParseService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(webhookController)
                .build();
    }

    @Test
    public void consume() throws Exception {

        doReturn("build passed")
                .when(travisParseService)
                .processRequest(any());

        mockMvc.perform(post("/travis-ci")
                .content("dummy content")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
                .andExpect(status().isAccepted())
                .andReturn();


    }

}