package AdventOfCode_7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class BuildHashTest {
    final String fileName = AdventOfCode_7.INPUT_FILE_NAME;
    final int testFileLineCount;
    final String[] diExample = {"0", "->", "c"};
    final String[] notWireExample = {"NOT", "kx", "->", "ky"};
    final String[] andWireExample = {"fs", "AND", "fu", "->", "fv"};
    final String[] orWireExample = {"fs", "OR", "fu", "->", "fv"};
    final String[] lShiftWireExample = {"fj", "LSHIFT", "15", "->", "fn"};
    final String[] rShiftWireExample = {"fj", "RSHIFT", "15", "->", "fn"};
    final String[] badInputMalformed = {"fj", "RSHIFT", "15", "->", "fn", "garbage"};
    final String[] badInputEmpty = {""};
    final String[] badInputNull = null;
    
    public BuildHashTest(){
        testFileLineCount = lineCount();
    }
    
    public final int lineCount(){
        Scanner lineInspect = null;
        String inputString = null;
        int itemsRead = 0;
        try {
            lineInspect = new Scanner(new BufferedReader(new FileReader(fileName)));
            lineInspect.useDelimiter("\n");
            while (lineInspect.hasNext()){
                itemsRead++;
                inputString = lineInspect.next();
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(BuildHashTest.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (lineInspect != null) {
                lineInspect.close();
            }
        }
        return itemsRead;
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
    public void testValidWireCheck(){
        System.out.println("testing BuildHash::validWireCheck");
        Wire newWire;
        String newWireName;
        
        newWireName = BuildHash.getWireName(diExample);
        newWire = BuildHash.makeNewWire(diExample);        
        assertTrue("valid DI wire failing to validate", BuildHash.validWireCheck(newWireName, newWire));
        
        newWireName = BuildHash.getWireName(notWireExample);
        newWire = BuildHash.makeNewWire(notWireExample);        
        assertTrue("valid NOT wire failing to validate", BuildHash.validWireCheck(newWireName, newWire));

        newWireName = BuildHash.getWireName(andWireExample);
        newWire = BuildHash.makeNewWire(andWireExample);        
        assertTrue("valid AND wire failing to validate", BuildHash.validWireCheck(newWireName, newWire));
        
        newWireName = BuildHash.getWireName(orWireExample);
        newWire = BuildHash.makeNewWire(orWireExample);        
        assertTrue("valid OR wire failing to validate", BuildHash.validWireCheck(newWireName, newWire));

        newWireName = BuildHash.getWireName(lShiftWireExample);
        newWire = BuildHash.makeNewWire(lShiftWireExample);        
        assertTrue("valid LSHIFT wire failing to validate", BuildHash.validWireCheck(newWireName, newWire));

        newWireName = BuildHash.getWireName(rShiftWireExample);
        newWire = BuildHash.makeNewWire(rShiftWireExample);        
        assertTrue("valid RSHIFT wire failing to validate", BuildHash.validWireCheck(newWireName, newWire));
        
        newWireName = BuildHash.getWireName(badInputMalformed);
        newWire = BuildHash.makeNewWire(badInputMalformed);        
        assertFalse("invalid wire (too many inputs) is validating", BuildHash.validWireCheck(newWireName, newWire));

        newWireName = BuildHash.getWireName(badInputEmpty);
        newWire = BuildHash.makeNewWire(badInputEmpty);        
        assertFalse("invalid wire (no inputs) is validating", BuildHash.validWireCheck(newWireName, newWire));
        
        newWireName = BuildHash.getWireName(badInputNull);
        newWire = BuildHash.makeNewWire(badInputNull);
        assertFalse("invalid wire (null input) is validating", BuildHash.validWireCheck(newWireName, newWire));
    }
    
    @Test
    public void testParseFile(){
        System.out.println("testing BuildHash::parseFile");
        assertEquals("hash is not adding every item in the given input file", BuildHash.parseFile(fileName, "", 0).size(), testFileLineCount);
        assertEquals("hash is not size zero despite empty input file", BuildHash.parseFile(null, "", 0).size(), 0);
    }
    
    @Test
    public void testGetWireName(){
        System.out.println("testing BuildHash::getWireName");
        String result = BuildHash.getWireName(badInputNull);
        assertEquals("should ignore null input", result, "");
        
        result = BuildHash.getWireName(badInputEmpty);
        assertEquals("should ignore empty strings", result, "");

        result = BuildHash.getWireName(diExample);
        assertEquals("failed to return wire name with 3 inputs", result, "c");

        result = BuildHash.getWireName(notWireExample);
        assertEquals("failed to return wire name with 4 inputs", result, "ky");

        result = BuildHash.getWireName(andWireExample);
        assertEquals("failed to return wire name with 5 inputs", result, "fv");
    }
    
    @Test
    public void testGetWireType(){
        System.out.println("testing BuildHash::getWireType");
        String result = BuildHash.getWireType(diExample);
        assertEquals("DI input not read as DI", result, "DI");

        result = BuildHash.getWireType(notWireExample);
        assertEquals("NOT input not read as NOT", result, "NOT");

        result = BuildHash.getWireType(andWireExample);
        assertEquals("AND input not read as AND", result, "AND");
        
        result = BuildHash.getWireType(orWireExample);
        assertEquals("OR input not read as OR", result, "OR");       
        
        result = BuildHash.getWireType(lShiftWireExample);
        assertEquals("LSHIFT input not read as LSHIFT", result, "LSHIFT");       

        result = BuildHash.getWireType(rShiftWireExample);
        assertEquals("RSHIFT input not read as RSHIFT", result, "RSHIFT");
        
        result = BuildHash.getWireType(badInputMalformed);
        assertEquals("Malformed wrong # of items input not left as Undefined Wire name", result, "Undefined");

        result = BuildHash.getWireType(badInputEmpty);
        assertEquals("Malformed empty input not left as Undefined Wire name", result, "Undefined");
        
        result = BuildHash.getWireType(badInputNull);
        assertEquals("Malformed null input not left as Undefined Wire name", result, "Undefined");
    }

    @Test
    public void testMakeNewWire(){
        System.out.println("testing BuildHash::makeNewWire");
        Wire newWire;
        
        newWire = BuildHash.makeNewWire(diExample);
        assertNotNull("DI input read as null", newWire);
        assertTrue("DI input did not generate DI object", newWire instanceof DiWire);

        newWire = BuildHash.makeNewWire(notWireExample);
        assertNotNull("NOT input read as null", newWire);
        assertTrue("NOT input did not generate NOT object", newWire instanceof NotWire);        

        newWire = BuildHash.makeNewWire(andWireExample);
        assertNotNull("AND input read as null", newWire);
        assertTrue("AND input did not generate AND object", newWire instanceof AndWire);
        
        newWire = BuildHash.makeNewWire(orWireExample);
        assertNotNull("OR input read as null", newWire);
        assertTrue("OR input did not generate OR object", newWire instanceof OrWire);        
        
        newWire = BuildHash.makeNewWire(lShiftWireExample);
        assertNotNull("LSHIFT input read as null", newWire);
        assertTrue("LSHIFT input did not generate LSHIFT object", newWire instanceof LShiftWire);        

        newWire = BuildHash.makeNewWire(rShiftWireExample);
        assertNotNull("RSHIFT input read as null", newWire);
        assertTrue("RSHIFT input did not generate RSHIFT object", newWire instanceof RShiftWire);        
        
        newWire = BuildHash.makeNewWire(badInputMalformed);
        assertNull("Malformed wrong # of items input did not return null Wire object", newWire);
        
        newWire = BuildHash.makeNewWire(badInputEmpty);
        assertNull("Malformed empty input did not return null Wire object", newWire);
        
        newWire = BuildHash.makeNewWire(badInputNull);
        assertNull("Malformed null input did not return null Wire object", newWire);        
    }
    
        @Test
        public void testMakeNewWire_Override(){
            System.out.println("testing BuildHash::makeNewWire (override)");
            Wire newWire;
            newWire = BuildHash.makeNewWire(diExample, "b", 5);
            assertNotNull("DI input read as null", newWire);
            assertTrue("DI input did not generate DI object", newWire instanceof DiWire);
        }    
}
