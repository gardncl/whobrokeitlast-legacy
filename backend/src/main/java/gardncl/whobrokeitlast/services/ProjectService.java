package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.models.Project;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private GithubService githubService;

    public Project insertProject(String projectTitle, String owner) throws IOException {

        Optional<Long> id = githubService.getRepos(owner)
                .stream()
                .filter(x -> x.getName().equals(projectTitle))
                .map(Repository::getId)
                .findFirst();

        if (!id.isPresent())
            throw new NoSuchElementException("Project "+projectTitle+" does not exist for user "+owner+".");

        Project project = new Project(id.get(), projectTitle);

        return projectDao.save(project);
    }
}
