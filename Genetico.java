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

            GABIL g = new GABIL(trainingData, 2000, 1.0/5.0, 0.5, 0.9);
//            Random gen = new Random();
//            for(int i = 0; i < 5000; i++) {
//                System.out.println( (int)  (1 + gen.nextDouble()*(3)));
//            }
//            ArrayList<int[]> ipop = g.initPop();
//            Iterator<int[]> t = ipop.iterator();
//            while (t.hasNext()) {
//                int[] k = t.next();
//                int[] k1 = new int[Parser.REP_SIZE];
//                int[] k2 = new int[Parser.REP_SIZE];
//                System.arraycopy(k, 0, k1, 0, Parser.REP_SIZE);
////                for(int i = Parser.REP_SIZE; i < Parser.REP_SIZE*2; i++) 
////                    k2[i % Parser.REP_SIZE] = k[i];
//                System.arraycopy(k, Parser.REP_SIZE, k2, 0, Parser.REP_SIZE);
//                p.test(k1);
//                System.out.println("Segunda regla");
//                System.out.println("===================================");
//                p.test(k2);
//                System.out.println("-----------------------------");
//            }
            int[] bestHyp = g.go();
            System.out.println("Best hypothesis");
            System.out.println(bestHyp);
            // Chequear solución
            Parser t = new Parser(args[1]);
            ArrayList<int[]> testData = t.goBalanced();
            
//            double overall;
//            int nrules = bestHyp.length / Parser.REP_SIZE;
//            int i = 0;
//            int acc = 0;
//            int index = 0;
//            // Ṕrobar cada regla contra el training set.
//            while (i < nrules) {
//                acc += testAgainstTS(bestHyp, index, testData);
//                i++;
//                index += Parser.REP_SIZE;
//            }
//            // Calcular fitness overall de la hipótesis
//            overall = (((double) acc*(100.0)) / ((double) trainingData.size())) 
//                    / (double) nrules;
//            overall = Math.pow(overall, 2);
//            
//            
//            int classified = testAgainstTS(bestHyp, index, testData);
//            
////        }
////        catch(ArrayIndexOutOfBoundsException e) {
////            System.out.println("Especificar archivo de entrada."+e);
////        }
//        
//    }
//    
//     public static int testAgainstTS(int[] hyp, int l, 
//             ArrayList<int[]> testData) {
//        Iterator<int[]> it = testData.iterator();
//        int classified = 0;
//        while (it.hasNext()) {
//            int[] example = it.next();
//            int field = 0;
//            int index = l;
//            boolean testOK = true;
//            while (testOK && field < Parser.FIELDS) {
//                testOK &= matchRulePortion(hyp, example, index,
//                        index + Parser.seq[field]);
//                index += Parser.seq[field];
//                field++;
//            }
//            if (testOK) {classified++;}
//        }
//        return classified;
//    }
//    
//    public static boolean matchRulePortion(int[] hyp, int[] example, int l, int h) {
//        int valid = 0;
//        // Check si se hace match con Method
//        if (l != 0 && (l % 35 == 0)) {
//            return (example[example.length - 1] == hyp[l]);
//        } else {
//            for(int i = l; i < h; i++) {
//                valid |= (hyp[i] & example[i % 36]);
//            }
//        }
//        return valid == 1;
//    }
//    
    }
}
