package ua.com.serzh;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRestAppApplicationTests extends TestCase {

    @Test
    public void contextLoads() {
    }

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public EmployeeRestAppApplicationTests(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */

    public static junit.framework.Test suite() {
        return new TestSuite(EmployeeRestAppApplicationTests.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

}
