package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dao.BreakDao;
import gardncl.whobrokeitlast.dao.DeveloperDao;
import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.dto.BreakDto;
import gardncl.whobrokeitlast.models.Break;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BrokenBuildServiceTest {

    @Spy
    @InjectMocks
    private BrokenBuildService brokenBuildService;

    @Mock
    private DeveloperDao developerDao;

    @Mock
    private BreakDao breakDao;

    @Mock
    private ProjectDao projectDao;

    private BreakDto breakDto;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        breakDto = new BreakDto("gardncl", "whobrokeitlast", "kockles","kockles@gmail.com");
    }

    @Test(expected = NoSuchElementException.class)
    public void saveBreakProjectDoesNotExist() {
        doReturn(null)
                .when(projectDao)
                .findByProjectTitle(anyString());


        brokenBuildService.saveBreak(breakDto);
    }

    @Test
    public void saveBreakDeveloperExists() {
        Project project = new Project();
        Developer developer = new Developer();
        doReturn(project)
                .when(projectDao)
                .findByProjectTitle(anyString());

        doReturn(developer)
                .when(developerDao)
                .getByUserName(anyString());

        doReturn(null)
                .when(developerDao)
                .save(any(Developer.class));

        doReturn(null)
                .when(breakDao)
                .save(any(Break.class));

        brokenBuildService.saveBreak(breakDto);

        verify(developerDao,times(1))
                .save(developer);
    }

    @Test
    public void saveBreak() {
        Project project = new Project();
        Developer developer = new Developer();
        doReturn(project)
                .when(projectDao)
                .findByProjectTitle(anyString());

        doReturn(null)
                .when(developerDao)
                .getByUserName(anyString());

        doReturn(null)
                .when(developerDao)
                .save(any(Developer.class));

        doReturn(null)
                .when(breakDao)
                .save(any(Break.class));

        brokenBuildService.saveBreak(breakDto);

        verify(developerDao,times(0))
                .save(developer);
    }


    @Test
    public void getLastBreak() throws Exception {
        final Developer developer1 = new Developer();
        final Developer developer2 = new Developer();
        final Developer developer3 = new Developer();

        final Project project = new Project(1L, "whobrokeitlast", developer1);
        final Break break1 = new Break(developer1, project, new Date(100000));
        final Break break2 = new Break(developer2, project, new Date(1000000));
        final Break break3 = new Break(developer3, project, new Date(1000000));
        final Break break4 = new Break(developer3, project, new Date(10000000));

        final List<Break> breakList = asList(break1, break2, break3, break4);

        project.setDevelopers(asList(developer1, developer2, developer3));

        doReturn(breakList).when(breakDao).findAllByProject_Id(1L);

        assertEquals(developer3, brokenBuildService.getLastBreak(project));
    }

}