package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.dao.DeveloperDao;
import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.dto.RepositoryDto;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import gardncl.whobrokeitlast.services.GithubService;
import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private GithubService githubService;

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private DeveloperDao developerDao;

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public List<RepositoryDto> getRepositoriesForUser(@PathVariable String user) throws IOException {
        return githubService
                .getRepoDtos(user)
                .stream()
                .map(this::isTracked)
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/{user}/project/{project}", method = RequestMethod.POST)
    public void projectSwitch(@PathVariable String user, @PathVariable String project) throws IOException {
        List<Repository> repositoryList = githubService.getRepos(user);
        Optional<Repository> repo = repositoryList
                .stream()
                .filter(x -> x.getName().equals(project))
                .findFirst();
        if (!repo.isPresent())
            throw new NoSuchElementException("Repository "+project+" from user "+user+" not found.");
        Developer owner = developerDao.getByUserName(user);
        Project projectEntry = new Project(repo.get().getId(), project, owner);
        Project databaseEntry = projectDao.findOne(projectEntry.getId());
        if (databaseEntry == null) {
            projectDao.save(projectEntry);
        } else {
            projectDao.delete(projectEntry.getId());
        }
    }

    private RepositoryDto isTracked(RepositoryDto repositoryDto) {
        Project project = projectDao.findOne(repositoryDto.id);
        repositoryDto.tracked = project != null;
        return repositoryDto;
    }
}
