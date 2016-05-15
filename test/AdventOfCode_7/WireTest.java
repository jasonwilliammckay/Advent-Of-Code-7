package AdventOfCode_7;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WireTest {
    
    public WireTest() {
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

    // Test of convertToShort method, of class Wire.
    @Test
    public void testConvertToShort() {
        System.out.println("testing WireTest::convertToShort");
        assertEquals("negative signed fails to convert to unsigned", Wire.convertToShort(-1), 65535);
        assertEquals("zero fails to convert to zero", Wire.convertToShort(0), 0);
        assertEquals("positive 16-bit int fails to maintain", Wire.convertToShort(1), 1);
        assertEquals("large positive 16-bit int fails to maintain", Wire.convertToShort(65534), 65534);
        assertEquals("max 16-bit int fails to maintain", Wire.convertToShort(65535), 65535);
        assertEquals("max+1 16-bit int fails to cycle back to zer0", Wire.convertToShort(65536), 0);
    }
    
    @Test
    public void testParseShift(){
        System.out.println("testing WireTest::parseShift");
        assertEquals("null input does not return -1", Wire.parseShift(null), -1);
        assertEquals("no input does not return -1", Wire.parseShift(""), -1);
        assertEquals("negative int does not convert to negative int", Wire.parseShift("-1"), -1);
        assertEquals("zero does not convert to zero", Wire.parseShift("0"), 0);
        assertEquals("positive int does not convert to positive int", Wire.parseShift("1"), 1);
        assertEquals("attempted word-to-integer conversion does not return -1", Wire.parseShift("apples"), -1);
    }
    
    @Test
    public void testInspectSourceForInt(){
        System.out.println("testing WireTest::inspectSourceForInt");
        assertTrue("negative int fails to report as int", Wire.inspectSourceForInt("-1"));
        assertTrue("zero fails to report as int", Wire.inspectSourceForInt("0"));
        assertTrue("positive int fails to report as int", Wire.inspectSourceForInt("1"));
        assertFalse("string incorrectly reports as int", Wire.inspectSourceForInt("apples"));
        assertFalse("null incorrectly reports as int", Wire.inspectSourceForInt(null));
        assertFalse("empty string incorrectly reports as int", Wire.inspectSourceForInt(""));
    }
}
