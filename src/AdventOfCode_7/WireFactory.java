/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode_7;

public class WireFactory {

    public static Wire newWire(String input, String[] inputs){
    
    switch(input){
        case "DI":
            return new DiWire(inputs[0]);
        case "NOT": 
            return new NotWire(inputs[1]);
        case "AND": 
            return new AndWire(inputs[0], inputs[2]);
        case "OR": 
            return new OrWire(inputs[0], inputs[2]);
        case "LSHIFT": 
            return new LShiftWire(inputs[0], inputs[2]);
        case "RSHIFT":
            return new RShiftWire(inputs[0], inputs[2]);
        default:
            break;
       }
    
    return null;
    }
}
