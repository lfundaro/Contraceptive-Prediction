package genetico;

import java.util.ArrayList;
import java.util.Iterator;

public class Genetico {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            Parser p = new Parser(args[0]);
            ArrayList<int[]> trainingData = p.goBalanced();
            
            GABIL g = new GABIL(trainingData, 5000, 4.0/5.0, 0.04, 0.8,3);

            int[] bestHyp = g.go();
            System.out.println("Mejor clasificador encontrado");
            System.out.print("Cantidad de 1s en la mejor hipotesis: ");
            g.howmany1s(bestHyp);
            g.printHyp(bestHyp);
            System.out.println("---");
//             Chequear solución
            
            Parser t = new Parser(args[1]);
            ArrayList<int[]> testData = t.goBalanced();
            
            int nrules = bestHyp.length / Parser.REP_SIZE;
            int acc = 0;
            Iterator<int[]> trIt = testData.iterator();
            while (trIt.hasNext()) {
                int[] tHyp = trIt.next();
                int i = 0;
                int index = 0;
                boolean success = false;
                while (!success && i < nrules) {
                    if(success = testAgainstTS(bestHyp, index, tHyp)) break;
                    i++;
                    index += Parser.REP_SIZE;
                }
                if (success) {acc++;}
            }
            
            // Calcular fitness overall de la hipótesis
            double overall = (((double) acc) / ((double) testData.size()));
            
            System.out.println("Clasificados = "+overall);
      
    }
        
    public static boolean testAgainstTS(int[] hyp, int l, int[] tHyp) {
        boolean classified = false;
        int field = 0;
        int index = l;
        boolean testOK = true;
        while (testOK && field < Parser.FIELDS) {
            testOK &= matchRulePortion(hyp, tHyp, index,
                    index + Parser.seq[field]);
            index += Parser.seq[field];
            field++;
        }
        if (testOK) {classified = true;}
        return classified;
    }
    
    public static boolean matchRulePortion(int[] hyp, int[] example, int l, int h) {
        int valid = 0;
        // Check si se hace match con Method
        if (l != 0 && (l % 35 == 0)) {
            return (example[example.length - 1] == hyp[l]);
        } else {
            for(int i = l; i < h; i++) {
                valid |= (hyp[i] & example[i % 36]);
            }
        }
        return valid == 1;
    }
}
