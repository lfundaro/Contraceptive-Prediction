package genetico;

import java.util.ArrayList;

public class Genetico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Parser p = new Parser(args[0]);
            ArrayList<int[]> trainingData = p.go();
            p.test(trainingData.get(0));
            GABIL g = new GABIL(trainingData, 10, 0.5, 0.0001);
            ArrayList<int[]> pop = g.initPop();
            for(int i = 0; i < pop.size(); i++) {
                int[] h = pop.get(i);
                p.test(h);
                System.out.println("------------------------");
            }
            
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Especificar archivo de entrada: "+e);
        }
        
    }
}
