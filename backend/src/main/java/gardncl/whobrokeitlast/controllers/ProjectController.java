package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import gardncl.whobrokeitlast.services.BrokenBuildService;
import gardncl.whobrokeitlast.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BrokenBuildService brokenBuildService;

    @RequestMapping(value = "/{id}/lastbreak")
    public Developer getLastBreak(@PathVariable("id") Long id) {
        return brokenBuildService.getLastBreak(projectDao.findOne(id));
    }

    @RequestMapping(value = "/{projectTitle}/owner/{owner}", method = RequestMethod.POST)
    public Project addProject(@PathVariable("projectTitle") String projectTitle, @PathVariable("owner") String owner) throws IOException {
        return projectService.insertProject(projectTitle, owner);
    }

}
