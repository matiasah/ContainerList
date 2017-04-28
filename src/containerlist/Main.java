/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package containerlist;

import java.util.Arrays;

/**
 *
 * @author Matías
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ContainerList cont = new ContainerList(5);
        
        for (int i = 0; i < 11; i++) {
            
            cont.set(i, i);
            
        }
        
        System.out.println(Arrays.toString(cont.toArray()));
        System.out.println(cont.countContainers());
        
    }
    
}
