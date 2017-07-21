package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dao.DeveloperDao;
import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.eclipse.egit.github.core.Repository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

public class ProjectServiceTest {

    @Spy
    @InjectMocks
    private ProjectService projectService;

    @Mock
    private ProjectDao projectDao;

    @Mock
    private DeveloperDao developerDao;

    @Mock
    private GithubService githubService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void insertProject() throws Exception {
        final String projectTitle = "whobrokeitlast";
        final String owner = "gardncl";
        final Long id = 1L;
        final List<Repository> repoList = new ArrayList<>();
        final Repository repository = new Repository();
        final Developer developer = new Developer();
        final Project project = new Project(id,projectTitle,developer);
        repository.setName(projectTitle);
        repository.setId(id);
        repoList.add(repository);

        doReturn(repoList)
                .when(githubService)
                .getRepos(owner);

        doReturn(developer)
                .when(developerDao)
                .getByUserName(owner);

        doReturn(project)
                .when(projectDao)
                .save(any(Project.class));

        Project result = projectService.insertProject(projectTitle, owner);

        assertEquals(id, result.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void insertProjectNoProject() throws Exception {
        final String projectTitle = "whobrokeitlast";
        final String owner = "gardncl";
        final List<Repository> repoList = new ArrayList<>();

        doReturn(repoList)
                .when(githubService)
                .getRepos(owner);

        projectService.insertProject(projectTitle, owner);
    }

    @Test(expected = NoSuchElementException.class)
    public void insertProjectNoUser() throws Exception {
        final String projectTitle = "whobrokeitlast";
        final String owner = "gardncl";
        final Long id = 1L;
        final List<Repository> repoList = new ArrayList<>();
        final Repository repository = new Repository();
        repository.setName(projectTitle);
        repository.setId(id);
        repoList.add(repository);

        doReturn(repoList)
                .when(githubService)
                .getRepos(owner);

        doReturn(null)
                .when(developerDao)
                .getByUserName(owner);

        projectService.insertProject(projectTitle, owner);
    }

}