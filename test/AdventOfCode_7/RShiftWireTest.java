package AdventOfCode_7;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RShiftWireTest {
    
    public RShiftWireTest() {
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
        System.out.println("testing RShiftWire::processWire");
        Wire newWire;
        
        int j = 1;
        
        for (int i = 0; i < 15; i++){
            j = j * 2;
            newWire = new RShiftWire(Integer.toString(j), "1");
            assertEquals("x >> 1 should always = 1/2x", newWire.processWire(), j/2);
        }

        newWire = new RShiftWire("0", "5");
        assertEquals("0 >> x should always = 0", newWire.processWire(), 0);
        
        newWire = new RShiftWire("1", "1");
        assertEquals("bits shifted below bit 0 should be removed", newWire.processWire(), 0);

        newWire = new RShiftWire("98304", "1");
        assertEquals("bits shifted into range should be kept", newWire.processWire(), 49152);
    }

    @Test
    public void testValidateWire(){
        System.out.println("testing RShiftWire::validateWire");
        HashMap<String, Wire> testHash = new HashMap();
        Wire newWire = new RShiftWire("64", "2");
        testHash.put("ab", newWire);
        assertEquals("cannot locate self-hash target", newWire.validateWire(testHash), 16);

        testHash = new HashMap();
        Wire newWire1 = new RShiftWire("64", "2");
        Wire newWire2 = new RShiftWire("aa", "2");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, [64,2]" "ab, [aa,2]"}. searches the ab hash, 
        // finds reference to aa. searches the aa hash, finds [64,2], 
        // RSHIFTS 64 by 2 to 16, returns that to ab, RSHIFTS that by 2, returns 4
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 4);

        testHash = new HashMap();
        newWire1 = new RShiftWire("16", "2");
        newWire2 = new RShiftWire("64", "aa");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, [16,2]" "ab, [64,aa]"}. searches the ab hash, 
        // finds reference to aa. searches the aa hash, finds [16,2], 
        // RSHIFTS 16 by 2 to 4, returns that to ab, RSHIFTS 64 that by 4, returns 4
        assertEquals("cannot locate nested hash target (input2)", newWire2.validateWire(testHash), 4);
        
    }  

}
