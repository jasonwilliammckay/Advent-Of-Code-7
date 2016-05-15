package AdventOfCode_7;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiWireTest {
    
    public DiWireTest() {
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

    @Test
    public void testProcessWire() {
        System.out.println("testing DiWire::processWire");
        Wire newWire;

        newWire = new DiWire("0");
        assertEquals("input of x >= 0 should return x", newWire.processWire(), 0);

        newWire = new DiWire("1");
        assertEquals("input of x >= 0 should return x", newWire.processWire(), 1);

        newWire = new DiWire("-1");
        assertEquals("input of a negative number should return its unsigned 16 bit", newWire.processWire(), 65535);

        newWire = new DiWire("131071");
        assertEquals("input >16 bits should return 16 bits", newWire.processWire(), 65535);
    }
    
    @Test
    public void testValidateWire(){
        System.out.println("testing DiWire::validateWire");
        HashMap<String, Wire> testHash = new HashMap();
        Wire newWire = new DiWire("0");
        testHash.put("ab", newWire);
        assertEquals("cannot locate self-hash target", newWire.validateWire(testHash), 0);

        testHash = new HashMap();
        Wire newWire1 = new DiWire("7");
        Wire newWire2 = new DiWire("aa");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, 7" "ab, aa"}, searches the ab hash, finds reference 
        // to aa. searches the aa hash, finds an integer (7), returns that
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 7);
    }
}
