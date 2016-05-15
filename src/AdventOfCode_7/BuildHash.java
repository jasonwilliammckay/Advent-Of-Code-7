package AdventOfCode_7;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

    /* walks through an input file, parsing each line into an array, then
       requests that other functions create Wire objects for addition to a 
       Hash. ultimately returns the completed Hash */

public class BuildHash {
    public static HashMap<String, Wire> parseFile(String filename, String override, int overrideValue){
        if (filename == null)
            filename = "";
        
        HashMap <String, Wire> wireHash = new HashMap<>();
        Scanner lineInspect = null;
        String lineReadResult;
        String[] stringSplitResult;
        int itemsRead = 0;
        
        try {
            lineInspect = new Scanner(new BufferedReader(new FileReader(filename)));
            lineInspect.useDelimiter("\n");
            
            while (lineInspect.hasNext()) {
                itemsRead++;
                lineReadResult = lineInspect.next();
                stringSplitResult = lineReadResult.split(" ");
                String newWireName = getWireName(stringSplitResult);
                Wire newWire;
                
                if (!override.isEmpty() && newWireName.equals(override))
                    newWire = makeNewWire(stringSplitResult, override, overrideValue);
                else
                    newWire = makeNewWire(stringSplitResult);
                
                if (validWireCheck(newWireName, newWire))
                    wireHash.put(newWireName, newWire);
            }
        } catch (FileNotFoundException ex) {

        } finally {
            if (lineInspect != null) {
                lineInspect.close();
            }
        }
        /* if the hash size doesn't match the data entry size, then 
           the hash is useless. dump it and return an empty hash instead */
        if (itemsRead != wireHash.size())
            wireHash = new HashMap<>(); 
        
        return wireHash;
    }

    /* safety check -- are both properties of the proposed addition to 
    hash valid? */
    
    public static boolean validWireCheck(String newWireName, Wire newWire){
        boolean testResult = false;
        
        if (newWireName.length() > 0 && newWire != null)
                testResult = true;
        
        return testResult;
    }
    
    // determines and return the name of the given wire from a line of input
    
    public static String getWireName(String inputs[]){
        String wireName = "";
        
        if (inputs != null && inputs.length > 1)
            wireName = inputs[inputs.length-1];

        return wireName;
    }
    
    // determines and returns the type of logic gate based on a line of input
    
    public static String getWireType(String inputs[]){
        if (inputs == null)
            inputs = new String[]{""};

        int wireLength = inputs.length;
        String wireName = "Undefined";        
        
        switch (wireLength){
            case 3:     // direct input; ex. 0 -> c
                wireName = "DI";
                break;
            case 4:     // NOT gate; ex. NOT lk -> ll
                wireName = "NOT";
                break;
            case 5:     // AND, OR, LSHIFT, RSHIFT; ex. dt LSHIFT 15 -> dx
                wireName = inputs[1];
                break;
            default:
                break;
        }        
        return wireName;
    }

    /* creates a new Wire subclass object, the type depending on input 
       specifications, then returns it */
    
    public static Wire makeNewWire(String inputs[]){
        if (inputs == null)
            inputs = new String[]{""};
        
        Wire newWire = WireFactory.newWire(getWireType(inputs), inputs);

        return newWire;
    }  
    
    // like makeNewWire() above with a different signature. instead of inspecting
    // the inputs to construct this Wire, we hardcode it to a direct input with a value
    // of the override. since the input is an integer, it will never recursively
    // call other wires and change its inputs again, so the output is stable
    public static Wire makeNewWire(String inputs[], String override, int overrideValue){
        Wire newWire = new DiWire(String.valueOf(overrideValue));
        return newWire;
    }
}