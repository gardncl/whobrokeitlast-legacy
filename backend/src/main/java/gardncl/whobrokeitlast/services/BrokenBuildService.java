package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BrokenBuildService {

    /**
     * Given a project return the date that it last broke.
     * @param project
     * @return Date
     */

    public Date getLastBreak(Project project) {
        return project
                .getDevelopers()
                .stream()
                .map(Developer::getLastBreak)
                .max(Date::compareTo)
                .get();
    }

}
