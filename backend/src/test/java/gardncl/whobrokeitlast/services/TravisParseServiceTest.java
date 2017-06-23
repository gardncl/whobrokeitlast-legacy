package gardncl.whobrokeitlast.services;

import gardncl.whobrokeitlast.dao.BreakDao;
import gardncl.whobrokeitlast.dao.DeveloperDao;
import gardncl.whobrokeitlast.dao.ProjectDao;
import gardncl.whobrokeitlast.models.Break;
import gardncl.whobrokeitlast.models.Developer;
import gardncl.whobrokeitlast.models.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.NoSuchElementException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TravisParseServiceTest {

    @Spy
    @InjectMocks
    private TravisParseService travisParseService;

    @Mock
    private PercentEncodingParseService parseService;

    @Mock
    private DeveloperDao developerDao;

    @Mock
    private BreakDao breakDao;

    @Mock
    private ProjectDao projectDao;

    private final String projectTitle = "whobrokeitlast";
    private final String userName = "gardncl";
    private final String payload = "%7B%22id%22%3A243457763%2C%22repository%22%3A%7B%22id%22%3A13442836%2C%22name%22%3A%22whobrokeitlast%22%2C%22owner_name%22%3A%22gardncl%22%2C%22url%22%3Anull%7D%2C%22number%22%3A%2249%22%2C%22config%22%3A%7B%22language%22%3A%22java%22%2C%22jdk%22%3A%5B%22oraclejdk8%22%5D%2C%22sudo%22%3Afalse%2C%22script%22%3A%22mvn+cobertura%3Acobertura%22%2C%22notifications%22%3A%7B%22webhooks%22%3A%7B%22urls%22%3A%5B%22http%3A%2F%2Flowcost-env.x8pcftmhaw.us-east-1.elasticbeanstalk.com%2F%22%5D%7D%7D%2C%22after_success%22%3A%5B%22bash+%3C%28curl+-s+https%3A%2F%2Fcodecov.io%2Fbash%29%22%5D%2C%22.result%22%3A%22configured%22%2C%22group%22%3A%22stable%22%2C%22dist%22%3A%22precise%22%7D%2C%22status%22%3A1%2C%22result%22%3A1%2C%22status_message%22%3A%22Errored%22%2C%22result_message%22%3A%22Errored%22%2C%22started_at%22%3A%222017-06-15T22%3A36%3A24Z%22%2C%22finished_at%22%3A%222017-06-15T22%3A44%3A39Z%22%2C%22duration%22%3A495%2C%22build_url%22%3A%22https%3A%2F%2Ftravis-ci.org%2Fgardncl%2Fwhobrokeitlast%2Fbuilds%2F243457763%22%2C%22commit_id%22%3A70635589%2C%22commit%22%3A%222b731dd7fb97d461a54e9b12038994c7243315c5%22%2C%22base_commit%22%3Anull%2C%22head_commit%22%3Anull%2C%22branch%22%3A%22develop%22%2C%22message%22%3A%22fail+try+2+for+webhook%22%2C%22compare_url%22%3A%22https%3A%2F%2Fgithub.com%2Fgardncl%2Fwhobrokeitlast%2Fcompare%2Fdbe08c2f0a2b...2b731dd7fb97%22%2C%22committed_at%22%3A%222017-06-15T22%3A36%3A14Z%22%2C%22author_name%22%3A%22gardncl%22%2C%22author_email%22%3A%22gardncl%40gmail.com%22%2C%22committer_name%22%3A%22gardncl%22%2C%22committer_email%22%3A%22gardncl%40gmail.com%22%2C%22matrix%22%3A%5B%7B%22id%22%3A243457764%2C%22repository_id%22%3A13442836%2C%22parent_id%22%3A243457763%2C%22number%22%3A%2249.1%22%2C%22state%22%3A%22finished%22%2C%22config%22%3A%7B%22language%22%3A%22java%22%2C%22jdk%22%3A%22oraclejdk8%22%2C%22sudo%22%3Afalse%2C%22script%22%3A%22mvn+cobertura%3Acobertura%22%2C%22notifications%22%3A%7B%22webhooks%22%3A%7B%22urls%22%3A%5B%22http%3A%2F%2Flowcost-env.x8pcftmhaw.us-east-1.elasticbeanstalk.com%2F%22%5D%7D%7D%2C%22after_success%22%3A%5B%22bash+%3C%28curl+-s+https%3A%2F%2Fcodecov.io%2Fbash%29%22%5D%2C%22.result%22%3A%22configured%22%2C%22group%22%3A%22stable%22%2C%22dist%22%3A%22precise%22%2C%22os%22%3A%22linux%22%7D%2C%22status%22%3A1%2C%22result%22%3A1%2C%22commit%22%3A%222b731dd7fb97d461a54e9b12038994c7243315c5%22%2C%22branch%22%3A%22develop%22%2C%22message%22%3A%22fail+try+2+for+webhook%22%2C%22compare_url%22%3A%22https%3A%2F%2Fgithub.com%2Fgardncl%2Fwhobrokeitlast%2Fcompare%2Fdbe08c2f0a2b...2b731dd7fb97%22%2C%22started_at%22%3A%222017-06-15T22%3A36%3A24Z%22%2C%22finished_at%22%3A%222017-06-15T22%3A44%3A39Z%22%2C%22committed_at%22%3A%222017-06-15T22%3A36%3A14Z%22%2C%22author_name%22%3A%22gardncl%22%2C%22author_email%22%3A%22gardncl%40gmail.com%22%2C%22committer_name%22%3A%22gardncl%22%2C%22committer_email%22%3A%22gardncl%40gmail.com%22%2C%22allow_failure%22%3Afalse%7D%5D%2C%22type%22%3A%22push%22%2C%22state%22%3A%22errored%22%2C%22pull_request%22%3Afalse%2C%22pull_request_number%22%3Anull%2C%22pull_request_title%22%3Anull%2C%22tag%22%3Anull%7D";
    private final MultiValueMap<String,String> body = new LinkedMultiValueMap();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        body.add("payload", payload);
    }


    @Test
    public void processRequestBrokenBuildNoBreak() throws Exception {
        MultiValueMap<String,String> body = new LinkedMultiValueMap();
        body.add("payload",        "%7B%22id%22%3A243457763%2C%22repository%22%3A%7B%22id%22%3A13442836%2C%22name%22%3A%22whobrokeitlast%22%2C%22owner_name%22%3A%22gardncl%22%2C%22url%22%3Anull%7D%2C%22number%22%3A%2249%22%2C%22config%22%3A%7B%22language%22%3A%22java%22%2C%22jdk%22%3A%5B%22oraclejdk8%22%5D%2C%22sudo%22%3Afalse%2C%22script%22%3A%22mvn+cobertura%3Acobertura%22%2C%22notifications%22%3A%7B%22webhooks%22%3A%7B%22urls%22%3A%5B%22http%3A%2F%2Flowcost-env.x8pcftmhaw.us-east-1.elasticbeanstalk.com%2F%22%5D%7D%7D%2C%22after_success%22%3A%5B%22bash+%3C%28curl+-s+https%3A%2F%2Fcodecov.io%2Fbash%29%22%5D%2C%22.result%22%3A%22configured%22%2C%22group%22%3A%22stable%22%2C%22dist%22%3A%22precise%22%7D%2C%22status%22%3A1%2C%22result%22%3A1%2C%22status_message%22%3A%22Success%22%2C%22result_message%22%3A%22Errored%22%2C%22started_at%22%3A%222017-06-15T22%3A36%3A24Z%22%2C%22finished_at%22%3A%222017-06-15T22%3A44%3A39Z%22%2C%22duration%22%3A495%2C%22build_url%22%3A%22https%3A%2F%2Ftravis-ci.org%2Fgardncl%2Fwhobrokeitlast%2Fbuilds%2F243457763%22%2C%22commit_id%22%3A70635589%2C%22commit%22%3A%222b731dd7fb97d461a54e9b12038994c7243315c5%22%2C%22base_commit%22%3Anull%2C%22head_commit%22%3Anull%2C%22branch%22%3A%22develop%22%2C%22message%22%3A%22fail+try+2+for+webhook%22%2C%22compare_url%22%3A%22https%3A%2F%2Fgithub.com%2Fgardncl%2Fwhobrokeitlast%2Fcompare%2Fdbe08c2f0a2b...2b731dd7fb97%22%2C%22committed_at%22%3A%222017-06-15T22%3A36%3A14Z%22%2C%22author_name%22%3A%22gardncl%22%2C%22author_email%22%3A%22gardncl%40gmail.com%22%2C%22committer_name%22%3A%22gardncl%22%2C%22committer_email%22%3A%22gardncl%40gmail.com%22%2C%22matrix%22%3A%5B%7B%22id%22%3A243457764%2C%22repository_id%22%3A13442836%2C%22parent_id%22%3A243457763%2C%22number%22%3A%2249.1%22%2C%22state%22%3A%22finished%22%2C%22config%22%3A%7B%22language%22%3A%22java%22%2C%22jdk%22%3A%22oraclejdk8%22%2C%22sudo%22%3Afalse%2C%22script%22%3A%22mvn+cobertura%3Acobertura%22%2C%22notifications%22%3A%7B%22webhooks%22%3A%7B%22urls%22%3A%5B%22http%3A%2F%2Flowcost-env.x8pcftmhaw.us-east-1.elasticbeanstalk.com%2F%22%5D%7D%7D%2C%22after_success%22%3A%5B%22bash+%3C%28curl+-s+https%3A%2F%2Fcodecov.io%2Fbash%29%22%5D%2C%22.result%22%3A%22configured%22%2C%22group%22%3A%22stable%22%2C%22dist%22%3A%22precise%22%2C%22os%22%3A%22linux%22%7D%2C%22status%22%3A1%2C%22result%22%3A1%2C%22commit%22%3A%222b731dd7fb97d461a54e9b12038994c7243315c5%22%2C%22branch%22%3A%22develop%22%2C%22message%22%3A%22fail+try+2+for+webhook%22%2C%22compare_url%22%3A%22https%3A%2F%2Fgithub.com%2Fgardncl%2Fwhobrokeitlast%2Fcompare%2Fdbe08c2f0a2b...2b731dd7fb97%22%2C%22started_at%22%3A%222017-06-15T22%3A36%3A24Z%22%2C%22finished_at%22%3A%222017-06-15T22%3A44%3A39Z%22%2C%22committed_at%22%3A%222017-06-15T22%3A36%3A14Z%22%2C%22author_name%22%3A%22gardncl%22%2C%22author_email%22%3A%22gardncl%40gmail.com%22%2C%22committer_name%22%3A%22gardncl%22%2C%22committer_email%22%3A%22gardncl%40gmail.com%22%2C%22allow_failure%22%3Afalse%7D%5D%2C%22type%22%3A%22push%22%2C%22state%22%3A%22success%22%2C%22pull_request%22%3Afalse%2C%22pull_request_number%22%3Anull%2C%22pull_request_title%22%3Anull%2C%22tag%22%3Anull%7D");

        when(parseService.percentEncodingToJson(anyString()))
                .thenCallRealMethod();

        travisParseService.processRequest(body);

        verify(parseService, times(0))
                .percentEncodingToJson(payload);

        verify(projectDao, times(0))
                .findByProjectTitle(projectTitle);

        verify(developerDao, times(0))
                .getByUserName(userName);

        verify(developerDao, times(0))
                .save(any(Developer.class));

        verify(breakDao, times(0))
                .save(any(Break.class));

    }

    @Test(expected = NoSuchElementException.class)
    public void processRequestBrokenBuildProjectDoesNotExist() throws Exception {

        when(parseService.percentEncodingToJson(anyString()))
                .thenCallRealMethod();

        //PROJECT DOES NOT EXIST IN DATABASE
        doReturn(null)
                .when(projectDao)
                .findByProjectTitle(projectTitle);

        travisParseService.processRequest(body);

        verify(parseService, times(1))
                .percentEncodingToJson(payload);

        verify(projectDao, times(1))
                .findByProjectTitle(projectTitle);

    }

    @Test
    public void processRequestBrokenBuildUserDoesNotExist() throws Exception {
        final Project project = new Project();

        when(parseService.percentEncodingToJson(anyString()))
                .thenCallRealMethod();

        doReturn(project)
                .when(projectDao)
                .findByProjectTitle(projectTitle);

        doReturn(null)
                .when(developerDao)
                .getByUserName(userName);

        doReturn(null)
                .when(developerDao)
                .save(any(Developer.class));

        doReturn(null)
                .when(breakDao)
                .save(any(Break.class));

        travisParseService.processRequest(body);

        verify(parseService, times(1))
                .percentEncodingToJson(payload);

        verify(projectDao, times(1))
                .findByProjectTitle(projectTitle);

        verify(developerDao, times(1))
                .getByUserName(userName);

        verify(developerDao, times(1))
                .save(any(Developer.class));

        verify(breakDao, times(1))
                .save(any(Break.class));

    }

    @Test
    public void processRequestBrokenBuildUserDoesExist() throws Exception {
        final Project project = new Project();
        final Developer developer = new Developer();

        when(parseService.percentEncodingToJson(anyString()))
                .thenCallRealMethod();

        doReturn(project)
                .when(projectDao)
                .findByProjectTitle(projectTitle);

        doReturn(developer)
                .when(developerDao)
                .getByUserName(userName);

        doReturn(null)
                .when(developerDao)
                .save(any(Developer.class));

        doReturn(null)
                .when(breakDao)
                .save(any(Break.class));

        travisParseService.processRequest(body);

        verify(parseService, times(1))
                .percentEncodingToJson(payload);

        verify(projectDao, times(1))
                .findByProjectTitle(projectTitle);

        verify(developerDao, times(1))
                .getByUserName(userName);

        verify(developerDao, times(1))
                .save(developer);

        verify(breakDao, times(1))
                .save(any(Break.class));

    }

}