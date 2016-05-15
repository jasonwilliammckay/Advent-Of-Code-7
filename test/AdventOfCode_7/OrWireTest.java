package AdventOfCode_7;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrWireTest {
    
    public OrWireTest() {
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
        System.out.println("testing WireTest::processWire");
        Wire newWire;
        
        newWire = new OrWire("0", "1");
        assertEquals("x | 0 should always = x", newWire.processWire(), 1);

        newWire = new OrWire("4", "4");
        assertEquals("x | x should always = x", newWire.processWire(), 4);

        newWire = new OrWire("65535", "131071");
        assertEquals("should round 16|(>16) bits to 16 bits", newWire.processWire(), 65535);        
        
        newWire = new OrWire("131071", "131071");
        assertEquals("should round (>16)|(>16) to 16 bits", newWire.processWire(), 65535);
        
        newWire = new OrWire("-1", "0");
        assertEquals("should convert negative to unsigned positive", newWire.processWire(), 65535);
    }
    
    @Test
    public void testValidateWire(){
        System.out.println("testing WireTest::validateWire");
        HashMap<String, Wire> testHash = new HashMap();
        Wire newWire = new OrWire("1", "2");
        testHash.put("ab", newWire);
        assertEquals("cannot locate self-hash target", newWire.validateWire(testHash), 3);

        testHash = new HashMap();
        Wire newWire1 = new OrWire("15", "240" );
        Wire newWire2 = new OrWire("aa", "3840");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, [15,240]" "ab, [aa,3840]"}, searches the ab hash, 
        // finds reference aa. searches the aa hash, finds [15,240], ORs to 255,
        // returns it to ab, ORs it to 3840, returns 4095
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 4095);

        testHash = new HashMap();
        newWire1 = new OrWire("240", "15");
        newWire2 = new OrWire("3840", "aa");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // as above test but inputs reversed to check input2 traversals
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 4095);        
      
    }

}
