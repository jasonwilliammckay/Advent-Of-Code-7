package AdventOfCode_7;

import java.util.HashMap;

interface WireInterface{
    public int processWire();   // every subclass much perform logic on its 
}                               // inputs in its own way

public abstract class Wire implements WireInterface{
    String input1 = "";
    String input2 = "";
    
    public Wire(String input1, String input2){
        this.input1 = input1;
        this.input2 = input2;
    }
    
    // converts a signed 32-it integer to a fake unsigned 16-bit integer
    static int convertToShort(int input){
        input &= 0x0000FFFF;
        return input;
    }
    
    // safely converts strings into integers, or throws an exception
    static int parseShift(String input){
        int output = -1;
        
        try{
            output = Integer.parseInt(input);
        }catch(NumberFormatException e){
        }
        return output;
    }
    
    // checks if a given String is an integer or not, returns true or false
    // doesn't actually convert anything
    static boolean inspectSourceForInt(String inspectMe){
        boolean result = false;
        
        try{
            int output = Integer.parseInt(inspectMe);
            result = true; // if it got here it's an integer
        }
        catch(NumberFormatException e){
        // if it got here.. do nothing, we assume false by default
        }        
        
        return result;
    }

    // if either of the inputs to a Wire is NOT an integer, it is referring to 
    // another wire, so go ask that wire for its integer outputs
    // if an input is empty, ignore it
    int validateWire(HashMap<String, Wire> wireHash) {
        
        if (!input1.isEmpty() && !inspectSourceForInt(input1))
            input1 = String.valueOf(wireHash.get(input1).validateWire(wireHash));
        if (!input2.isEmpty() && !inspectSourceForInt(input2))
            input2 = String.valueOf(wireHash.get(input2).validateWire(wireHash));
        
        return processWire();
    }
}

class DiWire extends Wire{

    public DiWire(String input1){
        super(input1, "");
    }
    
    @Override
    public int processWire(){
        int wireOut = parseShift(input1);
        wireOut = convertToShort(wireOut);
        return wireOut;
    }    
}    

class NotWire extends Wire{

    public NotWire(String input1){
        super(input1, "");
    }

    @Override
    public int processWire(){
        int wireOut = parseShift(input1);
        wireOut = ~wireOut;
        wireOut = convertToShort(wireOut);
        return wireOut;
    }    
}

class AndWire extends Wire{
   
    public AndWire(String input1, String input2){
        super(input1, input2);
    }

    @Override
    public int processWire(){
        int workingSet1 = parseShift(input1);
        int workingSet2 = parseShift(input2);
        int wireOut = (workingSet1 & workingSet2);
        wireOut = convertToShort(wireOut);
        return wireOut;
    }
}

class OrWire extends Wire{
    
    public OrWire(String input1, String input2){
        super(input1, input2);
    }

    @Override
    public int processWire(){
        int workingSet1 = parseShift(input1);
        int workingSet2 = parseShift(input2);
        int wireOut = workingSet1 | workingSet2;
        wireOut = convertToShort(wireOut);
        return wireOut;
    }
}

class LShiftWire extends Wire{
    
    public LShiftWire(String input1, String shiftAmt){
        super(input1, shiftAmt);
    }    

    @Override
     public int processWire(){
        int valueToShift = parseShift(input1);
        int wireOut = valueToShift << parseShift(input2);
        wireOut = convertToShort(wireOut);
        return wireOut;
    }
}

class RShiftWire extends Wire{
    
    public RShiftWire(String input1, String shiftAmt){
        super(input1, shiftAmt);
    }

    @Override
     public int processWire(){
        int valueToShift = parseShift(input1);
        int wireOut = valueToShift >> parseShift(input2);
        wireOut = convertToShort(wireOut);
        return wireOut;
    }
}
