package gardncl.whobrokeitlast.services;

import org.eclipse.egit.github.core.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class GithubServiceTest {

    @Spy
    private GithubService githubService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRepos() throws Exception {
        final String username = "gardncl";
        final String repository = "whobrokeitlast";

        List<String> repositories = githubService
                .getRepos(username)
                .stream()
                .map(Repository::getName)
                .collect(Collectors.toList());

        assertTrue(repositories.contains(repository));
    }

}