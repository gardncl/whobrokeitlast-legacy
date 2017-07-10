package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.dto.RepositoryDto;
import gardncl.whobrokeitlast.services.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private GithubService githubService;

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public List<RepositoryDto> getRepositoriesForUser(@PathVariable String user) throws IOException {
        return githubService.getRepoDtos(user);
    }
}
