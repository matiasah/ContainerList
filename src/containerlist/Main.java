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
        
        for (int i = 0; i < 15; i++) {
            
            cont.set(i, i);
            
        }
        
        System.out.println("Array: " + Arrays.toString(cont.toArray()));
        System.out.println("Containers: " + cont.countContainers());
        System.out.println("Size: " + cont.size());
        System.out.println("Position of 6: " + cont.indexOf(6));
        
        for (Object o : cont) {
            
            System.out.println("Object: "+o);
            
        }
        
        ContainerList cont2 = new ContainerList(cont);
        
        for (Object o : cont2) {
            
            System.out.println("Object 2: " + o);
            
        }
        
        System.out.println("Size: " + cont2.size());
        
    }
    
}
