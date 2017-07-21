package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dto.RepositoryDto;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GithubService {

    public List<Repository> getRepos(String username) throws IOException {
        RepositoryService service = new RepositoryService();
        return service.getRepositories(username);


//        //OFFLINE USAGE
//        List<Repository> offlineList = new ArrayList<>();
//        Repository wbil = new Repository();
//        User owner = new User();
//        owner.setName("gardncl");
//        wbil.setId(91140445);
//        wbil.setName("whobrokeitlast");
//        wbil.setOwner(owner);
//        offlineList.add(wbil);
//
//        return offlineList;
    }



    public List<RepositoryDto> getRepoDtos(String username) throws IOException {
        return getRepos(username)
                .stream()
                .map(RepositoryDto::new)
                .collect(Collectors.toList());
    }
}
