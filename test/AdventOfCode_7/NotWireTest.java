package AdventOfCode_7;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NotWireTest {
    
    public NotWireTest() {
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
        System.out.println("testing NotWire::processWire");
        Wire newWire;
        
        newWire = new NotWire("0");
        assertEquals("~0 should always = 65535", newWire.processWire(), 65535);

        newWire = new NotWire("1");
        assertEquals("x should always convert to 65535 - x", newWire.processWire(), 65534);

        newWire = new NotWire("-1");
        assertEquals("-1 should convert to 0", newWire.processWire(), 0);
        
        newWire = new NotWire("1044480");
        assertEquals("> 16 bit numbers should NOT then convert to 16 bits", newWire.processWire(), 4095);
    }

    @Test
    public void testValidateWire(){
        System.out.println("testing NotWire::validateWire");
        HashMap<String, Wire> testHash = new HashMap();
        Wire newWire = new NotWire("0");
        testHash.put("ab", newWire);
        assertEquals("cannot locate self-hash target", newWire.validateWire(testHash), 65535);

        testHash = new HashMap();
        Wire newWire1 = new NotWire("7");
        Wire newWire2 = new NotWire("aa");
        testHash.put("aa", newWire1);
        testHash.put("ab", newWire2); 
        // hashes are {"aa, 7" "ab, aa"}, searches the ab hash, finds reference 
        // to aa. searches the aa hash, finds an integer (7), NOTs that to 65528,
        // returns it to ab, NOTs it back to 7, returns that
        assertEquals("cannot locate nested hash target", newWire2.validateWire(testHash), 7);
    }
}    