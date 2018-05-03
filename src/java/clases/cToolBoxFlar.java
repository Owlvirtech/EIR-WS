/*
 * Developer: Flores López Angel Raymundo
 * Owlvirtech Inc.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author angelraymundo
 */
public class cToolBoxFlar {
    public cToolBoxFlar(){
        
    }
    public ArrayList<String> ParseArray(String[] arry){
        ArrayList<String> arra = new ArrayList<>();
        for(int i=0;i<arry.length;i++){
            arra.add(arry[i]);
        }
        return arra;
    }
}
