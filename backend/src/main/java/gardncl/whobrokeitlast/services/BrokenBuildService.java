package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.springframework.stereotype.Service;

@Service
public class BrokenBuildService {

    /**
     * Given a project return the developer that broke it last.
     * @param project
     * @return Date
     */

    public Developer getLastBreak(Project project) {
        return project
                .getDevelopers()
                .stream()
                .max(Developer::compareTo)
                .get();
    }

}
