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
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Especificar archivo de entrada: "+e);
        }
        
    }
}
