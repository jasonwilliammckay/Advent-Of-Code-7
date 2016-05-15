package AdventOfCode_7;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainProgramTest {

    final String fileName = AdventOfCode_7.INPUT_FILE_NAME;
    
    public MainProgramTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /* removed the default main() test -- main doesn't do any processing, it 
    just calls other functions, which are themselves tested, and prints output */
    
    @Test
    public void testVerifyInputFile() {
        System.out.println("testing AdventOfCode_7::verifyInputFile");
        assertTrue("failed to find an existing file", AdventOfCode_7.verifyInputFile(fileName));
        assertFalse("improperly identified a null file as existing", AdventOfCode_7.verifyInputFile(null));
        assertFalse("improperly identified a fake file as existing", AdventOfCode_7.verifyInputFile("fakefile.txt"));
        assertFalse("improperly identified no file as a file", AdventOfCode_7.verifyInputFile(""));
    }
}