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
            GABIL g = new GABIL(trainingData, 10, 0.5, 0.0001, 1.0);

            int [] hyp = {0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,};
            Boolean xxx = g.validHyp(hyp);
            if (xxx) System.out.println("RIGHT"); else System.out.println("WRONG");

        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Especificar archivo de entrada: "+e);
        }
        
    }
}
