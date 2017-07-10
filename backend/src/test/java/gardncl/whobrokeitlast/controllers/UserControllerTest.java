package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.services.GithubService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserControllerTest {

    @Spy
    @InjectMocks
    private UserController userController;

    @Mock
    private GithubService githubService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void getRepositoriesForUser() throws Exception {
        final String owner = "gardncl";
        MvcResult mvcResult = mockMvc
                .perform(get("/user/{user}",owner))
                .andReturn();

        String string = mvcResult.getResponse().getContentAsString();
    }

}