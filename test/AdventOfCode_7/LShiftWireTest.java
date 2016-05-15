package AdventOfCode_7;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LShiftWireTest {
    
    public LShiftWireTest() {
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
        System.out.println("testing LShiftWire::processWire");
        Wire newWire;
        
        int j = 1;
        
        for (int i = 0; i < 15; i++){
            newWire = new LShiftWire(Integer.toString(j), "1");
            assertEquals("x << 1 should always = 2x", newWire.processWire(), j*2);
            j = j * 2;
        }

        newWire = new LShiftWire("0", "5");
        assertEquals("0 << x should always = 0", newWire.processWire(), 0);
        
        newWire = new LShiftWire("32768", "1");
        assertEquals("bits shifted above 16 should be removed", newWire.processWire(), 0);

        newWire = new LShiftWire("49152", "1");
        assertEquals("bits shifted above 16 should be removed", newWire.processWire(), 32768);
    }

    @Test
    public void testValidateWire(){
        System.out.println("testing LShiftWire::validateWire");
        HashMap<String, Wire> testHash = new HashMap();
        Wire newWire = new LShiftWire("2", "2");
        testHash.put("ab", newWire);
        assertEquals("cannot locate self-hash target", newWire.validateWire(testHash), 8);

        testHash = new HashMap();
        Wire newWire1 = new LShiftWire("2", "2");
        Wire newWire2 = new LShiftWire("aa", "2");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, [2,2]" "ab, [aa,2]"}. searches the ab hash, 
        // finds reference to aa. searches the aa hash, finds [2,2], 
        // LSHIFTS 2 by 2 to 8, returns that to ab, LSHIFTS that by 2, returns 32
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 32);

        testHash = new HashMap();
        newWire1 = new LShiftWire("2", "2");
        newWire2 = new LShiftWire("2", "aa");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // same as above test, with inputs reversed to test input2 traversal
        // 2<<2 = 8, then 2<<8 = 512
        assertEquals("cannot locate nested hash target (input2)", newWire2.validateWire(testHash), 512);
    }    

}
