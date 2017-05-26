package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.services.BrokenBuildService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest {

    @Spy
    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectDao projectDao;

    @Mock
    private BrokenBuildService brokenBuildService;

    private MockMvc mockMvc;
    private final String baseUrl = "/project/{id}";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                    .standaloneSetup(projectController)
                    .build();
    }

    @Test
    public void getLastBreak() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(baseUrl + "/lastbreak",0))
                .andExpect(status().isOk())
                .andReturn();
    }

}