package AdventOfCode_7;

import static AdventOfCode_7.AdventOfCode_7.INPUT_FILE_NAME;
import static AdventOfCode_7.AdventOfCode_7.verifyInputFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class AdventOfCode_7 {

    final static String INPUT_FILE_NAME = "input.txt";
    final static String FIND_WIRE = "a";
    final static String OVERRIDE = "b";
    
    public static void main(String[] args) {
        
        if (verifyInputFile(INPUT_FILE_NAME)){
            int result1 = processResult1();
            int result2 = processResult2(result1);
            System.out.printf("Part1 solution is %d. \n", result1);
            System.out.printf("Part2 solution is %d. \n", result2);
        }
        else
            System.out.printf("Invalid File: %s", INPUT_FILE_NAME);        
    }
    
    /// checks if a given input file exists in the program directory
    public static boolean verifyInputFile(String inputFileName){
        if (inputFileName == null)
            inputFileName = "";
        
        Path fileName = Paths.get(inputFileName);
        boolean fileExists = Files.isRegularFile(fileName) & Files.isReadable(fileName);        
        return fileExists;
    }    
    
    // constructs a hash and then queries a given Wire node for its output
    // based on inputs. any inputs which refer to wires are queried in
    // turn, recursively
    public static int processResult1(){
        HashMap <String, Wire> wireHash;
        int result;
        wireHash = BuildHash.parseFile(INPUT_FILE_NAME, "", -1);
        result = wireHash.get(FIND_WIRE).validateWire(wireHash);
        return result;
    }

    // much like processResult1(), but reset the nodes, and override one node 
    // of choice with the results from the first processResult1()
    public static int processResult2(int resultFrom1){
        HashMap <String, Wire> wireHash;
        int result;
        wireHash = BuildHash.parseFile(INPUT_FILE_NAME, OVERRIDE, resultFrom1);
        result = wireHash.get(FIND_WIRE).validateWire(wireHash);
        return result;
    }
}




