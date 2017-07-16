package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dao.BreakDao;
import gardncl.whobrokeitlast.dao.DeveloperDao;
import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.dto.BreakDto;
import gardncl.whobrokeitlast.models.Break;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class BrokenBuildService {

    @Autowired
    private DeveloperDao developerDao;

    @Autowired
    private BreakDao breakDao;

    @Autowired
    private ProjectDao projectDao;

    public void saveBreak(BreakDto breakDto) {
        Date timeOfBreak = new Date();

        //VERIFY THAT THE PROJECT EXISTS IN THE DATABASE
        Project project = projectDao.findByProjectTitle(breakDto.projectTitle);
        if (project == null)
            throw new NoSuchElementException("That project does not exist in the database");

        Developer developer = developerDao.getByUserName(breakDto.committerName);
        //IS THIS DEVELOPER ALREADY IN OUR DATABASE? IF NOT CREATE THEM
        //AND ATTACH THEM TO THIS PROJECT
        if (developer == null)
            developer = new Developer(breakDto.committerName,breakDto.committerEmail, project);
        //SET THEIR LAST BREAK TO THIS ONE AND SAVE THEM TO THE DATABASE
        developer.setLastBreak(timeOfBreak);
        developerDao.save(developer);

        //CREATE A NEW BROKEN BUILD AND SAVE IT TO THE BREAK TABLE
        Break current = new Break(developer, project, timeOfBreak);
        breakDao.save(current);
    }

    /**
     * Given a project return the developer that broke it last.
     * @param project
     * @return Date
     */
    public Developer getLastBreak(Project project) {
        return breakDao
                .findAllByProject_Id(project.getId())
                .stream()
                .max(Break::compareTo)
                .map(Break::getDeveloper)
                .get();
    }

}
