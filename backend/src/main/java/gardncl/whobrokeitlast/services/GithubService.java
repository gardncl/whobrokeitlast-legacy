package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dto.RepositoryDto;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    public List<Repository> getRepos(String username) throws IOException {
        RepositoryService service = new RepositoryService();
        return service.getRepositories(username);
    }

    public List<RepositoryDto> getRepoDtos(String username) throws IOException {
        return getRepos(username)
                .stream()
                .map(RepositoryDto::new)
                .collect(Collectors.toList());
    }
}
