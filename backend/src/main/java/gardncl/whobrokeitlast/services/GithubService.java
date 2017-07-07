package gardncl.whobrokeitlast.services;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GithubService {

    public List<Repository> getRepos(String username) throws IOException {
        RepositoryService service = new RepositoryService();
        return service.getRepositories(username);
    }
}
