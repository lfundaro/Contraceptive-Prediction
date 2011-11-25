package genetico;

import java.util.ArrayList;
import java.util.Iterator;

public class Genetico {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
            Parser p = new Parser(args[0]);
            ArrayList<int[]> trainingData = p.goBalanced();
            
//            int[] prueba = trainingData.get(0);
//            for(int i = 0; i < prueba.length; i++)
//                System.out.print(prueba[i]);
//            System.out.println();
//            int[] cprueba = {1,0,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,1,0,1,0,0,0,0,1,0,1,0,1};
            int[] clasificador = {0,0,1,1,0,0,1,1,1,1,1,0,0,1,1,0,1,1,0,0,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0,1,1,0,0,1,1,1,1,1,0,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,0,0,1,0,0,0,0,0,1,1,1,1,0,1,1,1,1,1,0,0,1,1,0,1,0,1,0,0,0,1,0,1,1,1,1,1,0,0,1,0,1,1,1,1,0,0,1,1,1,1,1,0,1,0,1,0,1,1,0,1,1,1,1,1,0,1,0,1,1,0,1,3,0,1,0,0,0,1,0,1,1,1,1,1,0,0,1,1,0,1,0,0,0,1,0,1,1,1,1,1,0,0,1,1,1,1,1,1,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,1,1,0,1,0,0,1,1,0,1,0,1,0,0,1,1,1,0,1,0,2,1,1,1,0,0,0,0,1,1,1,1,0,1,0,0,1,1,1,0,1,1,1,0,1,0,0,1,0,0,0,0,1,1,0,1,1,1,0,1,1,0,0,0,0,1,0,1,1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1,0,2,0,0,0,0,1,0,0,1,1,0,0,0,1,1,1,1,1,1,1,1,0,1,0,1,1,1,1,1,0,1,1,1,1,1,0,1,};
            GABIL g = new GABIL(trainingData, 5000, 4.0/5.0, 0.04, 0.7,3);
//            if (g.testAgainstTS(prueba, 0, cprueba)) {
//                System.out.println("It works !");
//            }
//            else {
//                System.out.println("It doesn't :(");
//        
//            int[] bestHyp = g.go();
//            System.out.println("Best hypothesis");
//            System.out.println(bestHyp);
//             Chequear solución
            
            Parser t = new Parser(args[0]);
            ArrayList<int[]> testData = t.goBalanced();
            
            int nrules = clasificador.length / Parser.REP_SIZE;
            int acc = 0;
            Iterator<int[]> trIt = testData.iterator();
            while (trIt.hasNext()) {
                int[] tHyp = trIt.next();
                int i = 0;
                int index = 0;
                boolean success = false;
                while (!success && i < nrules) {
                    if(success = testAgainstTS(clasificador, index, tHyp)) break;
                    i++;
                    index += Parser.REP_SIZE;
                }
                if (success) {acc++;}
            }
            //            System.out.println("training data size = "+trainingData.size());
            //            if (acc > 20)
            //                System.out.println("Acc = "+acc);
            // Ṕrobar cada regla contra el training set.
            
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
