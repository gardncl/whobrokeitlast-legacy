package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import gardncl.whobrokeitlast.services.BrokenBuildService;
import gardncl.whobrokeitlast.services.ProjectService;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectControllerTest {

    @Spy
    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectDao projectDao;

    @Mock
    private BrokenBuildService brokenBuildService;

    @Mock
    private ProjectService projectService;

    private MockMvc mockMvc;
    private final String baseUrl = "/project";

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
                .perform(get(baseUrl + "/{id}/lastbreak",0))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addProject() throws Exception {
        final String projectTitle = "whobrokeitlast";
        final String owner = "gardncl";
        final Developer developer = new Developer();
        developer.setUserName(owner);

        doReturn(new Project(1L,projectTitle,new Developer()))
                .when(projectService)
                .insertProject(projectTitle,owner);

        MvcResult mvcResult = mockMvc
                .perform(post(baseUrl + "/{projectTitle}/owner/{owner}",projectTitle,owner))
                .andExpect(status().isOk())
                .andReturn();

        verify(projectService,times(1))
                .insertProject(projectTitle, owner);

    }


}