package gardncl.whobrokeitlast;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.*;

public class TestServiceTest {

    @Spy
    private TestService testService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() throws Exception {
        assertEquals(0, testService.test());
    }

}