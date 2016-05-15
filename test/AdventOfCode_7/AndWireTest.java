package AdventOfCode_7;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AndWireTest {
    
    public AndWireTest() {
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
        System.out.println("testing AndWire::makeNewWire");
        Wire newWire;
        
        newWire = new AndWire("0", "1");
        assertEquals("integer & 0 should always = 0", newWire.processWire(), 0);

        newWire = new AndWire("4", "4");
        assertEquals("integer & itself should always = itself", newWire.processWire(), 4);

        newWire = new AndWire("255", "65280");
        assertEquals("integers with no bits in common should equal 0", newWire.processWire(), 0);
        
        newWire = new AndWire("131071", "131071");
        assertEquals("should round 17 bits to 16 bits", newWire.processWire(), 65535);

        newWire = new AndWire("-1", "-1");
        assertEquals("should convert negative to unsigned positive", newWire.processWire(), 65535);
    }

    @Test
    public void testValidateWire(){
        System.out.println("testing AndWire::validateWire");
        HashMap<String, Wire> testHash = new HashMap();
        Wire newWire = new AndWire("2", "6");
        testHash.put("ab", newWire);
        assertEquals("cannot locate self-hash target", newWire.validateWire(testHash), 2);

        testHash = new HashMap();
        Wire newWire1 = new AndWire("65520", "4095" );
        Wire newWire2 = new AndWire("aa", "255");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, [65520,4095]" "ab, [aa,255]"}, searches the ab hash, finds reference 
        // to aa. searches the aa hash, finds [65520,4095], ANDs to 4080,
        // returns it to ab, ANDs it to 255, returns 240
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 240);
        
        testHash = new HashMap();
        newWire1 = new AndWire("4095", "65520");
        newWire2 = new AndWire("255", "aa");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // same as above but testing recursion along input2
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 240);        
      
    }

}
