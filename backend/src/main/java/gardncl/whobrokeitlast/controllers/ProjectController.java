package gardncl.whobrokeitlast.controllers;

import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.services.BrokenBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/project/{id}")
public class ProjectController {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private BrokenBuildService brokenBuildService;

    @RequestMapping(value = "/lastbreak")
    public Date getLastBreak(@PathVariable("id") Long id) {
        return brokenBuildService
                .getLastBreak(projectDao.findOne(id));
    }

}
