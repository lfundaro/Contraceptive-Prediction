
package genetico;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Lorenzo Fundaró <lorenzofundaro [at] yahoo.com>
 */
public class GABIL {
    ArrayList<int[]> trainingData;
    int p;     // tamaño de población.
    double r;   // fracción de la población que será reemplazada.
    double m;   // Tasa de mutación.
    
    public GABIL(ArrayList<int[]> training, int p, double r, double m) {
        trainingData = training;
        this.p = p;
        this.r = r;
        this.m = m;
    }
    
    public ArrayList<int[]> initPop() {
        // Generación de una hipótesis aleatoria
        ArrayList<int[]> pool = new ArrayList<int[]>(p);
        Random gen = new Random();
        int[] hyp;
        int index;
        for(int i = 0; i < p; i++) {
            index = 0;
            hyp = new int[Parser.REP_SIZE];
            init(hyp);
            // Edad
            index = genField(hyp, gen, index, index + Parser.AGE_SIZE, "AGE");
            // Educación de la esposa
            index = genField(hyp, gen, index, index + Parser.CAT_SIZE, "CAT");
            // Educación del esposo
            index = genField(hyp, gen, index, index + Parser.CAT_SIZE, "CAT");
            // Número de hijos
            index = genField(hyp, gen, index, index + Parser.NCHILD_SIZE,
                    "CHILD");
            // Religión
            index = genField(hyp, gen, index, index + Parser.REL_SIZE, "REL");
            // Situación trabajo esposa
            index = genField(hyp, gen, index, index + Parser.WORK_SIZE, "WORK");
            // Situación del esposo
            index = genField(hyp, gen, index, index + Parser.CAT_SIZE, "CAT");
            // Estándar de vida
            index = genField(hyp, gen, index, index + Parser.CAT_SIZE, "CAT");
            // Exposición al ambiente
            index = genField(hyp, gen, index, index + Parser.MEX_SIZE, "MEX");
            // Método anticonceptivo
            genMethodField(hyp, gen, index, index + Parser.MTH_SIZE);
            pool.add(hyp);
        }
        return pool;
    }
    
        private void genMethodField(int[] hyp, Random gen, int l, int h) {
            double g = gen.nextDouble();
            int method = 1 + (int) (g*(3));
            for(int i = l; i < h; i++)
                hyp[i] = method;
            return;
        }
  
    
    private int genField(int[] hyp, Random gen, int l, int h, String type) {
        double g = gen.nextDouble();
        int age;
        while (!validGen(hyp, l, h)) {
            if (g <= 0.5) {
                // Random bit-a-bit
                for(int i = l; i < h; i++) {
                    g = gen.nextDouble();
                    if (g <= 0.5)   // Bit prendido
                        hyp[i] = 1;
                    else           // Bit apagado
                        hyp[i] = 0;
                }
            }
            else {
                // Random global
                g = gen.nextDouble();
                int w;
                if (type.compareTo("AGE") == 0) {
                    w = Parser.MIN_AGE + (int) (g*(Parser.MAX_AGE -
                            Parser.MIN_AGE + 1));
                    Parser.calcAge(hyp, w, l);
                } else if (type.compareTo("CAT") == 0) {
                    w = 1 + (int) (g*(Parser.CAT_SIZE));
                    Parser.calCat(hyp, w, l);
                } else if (type.compareTo("CHILD") == 0) {
                    w = Parser.MIN_CHILD + (int) (g*(Parser.MAX_CHILD -
                            Parser.MIN_CHILD + 1));
                    Parser.numChildren(hyp, w, l);
                } else if (type.compareTo("REL") == 0) {
                    w = (int) (g*(2));
                    Parser.calRel(hyp, w, l);
                } else if (type.compareTo("WORK") == 0) {
                    w = (int) (g*(2));
                    Parser.calWorking(hyp, w, l);
                } else if (type.compareTo("MEX") == 0) {
                    w = (int) (g*(2));
                    Parser.calMediaExp(hyp, w, l);
                } 
            }
            g = gen.nextDouble();
        }
        return h;
    }
    
    private boolean validGen(int[] hyp, int l, int h) {
        int valid = 0;
        for(int i = l; i < h; i++) {
            valid = valid | hyp[i];
        }
        return valid == 1;
    }
    
    private void init(int[] ex) {
        for(int i = 0; i < ex.length; i++)
            ex[i] = 0;
    }
    
    public int[] go() {
        int[] finalHyp = new int[20];
        return finalHyp;
    }
}
