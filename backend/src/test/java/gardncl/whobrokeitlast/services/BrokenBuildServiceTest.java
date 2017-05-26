package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class BrokenBuildServiceTest {

    @Spy
    private BrokenBuildService brokenBuildService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getLastBreak() throws Exception {
        final Project project = new Project();
        final Developer developer1 = new Developer();
        final Developer developer2 = new Developer();
        final Developer developer3 = new Developer();

        developer1.setLastBreak(new Date(100000));
        developer2.setLastBreak(new Date(1000000));
        developer3.setLastBreak(new Date(10000000));
        project.setDevelopers(Arrays.asList(developer1, developer2, developer3));

        assertEquals(developer3, brokenBuildService.getLastBreak(project));
    }

}