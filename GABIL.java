
package genetico;

import java.util.ArrayList;
import java.util.Iterator;
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
    double fitness_threshold;
    
    public GABIL(ArrayList<int[]> training, int p, double r, double m, 
            double fthld) {
        trainingData = training;
        this.p = p;
        this.r = r;
        this.m = m;
        fitness_threshold = fthld;
    }
    
    private ArrayList<int[]> initPop() {
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
    
    private double[] fitness(ArrayList<int[]> pool) {
        Iterator<int[]> it = pool.iterator();
        double[] ftn = new double[pool.size()];
        int cnt = 0;
        double overall = 0.0;
        while (it.hasNext()) {
            int[] hyp = it.next();
            int nrules = hyp.length / Parser.REP_SIZE;
            int i = 0;
            int acc = 0;
            int index = 0;
            // Ṕrobar cada regla contra el training set.
            while (i < nrules) {
                acc += testAgainstTS(hyp, index);
                i++;
                index += Parser.REP_SIZE;
            }
            // Calcular fitness overall de la hipótesis
            overall = ((double) acc) / ((double) trainingData.size());
            // Asignar a la i-ésima hipótesis su valor fitness
            ftn[cnt] = overall;
            cnt++;
        }
        return ftn;
    }
    
    private int testAgainstTS(int[] hyp, int l) {
        Iterator<int[]> it = trainingData.iterator();
        int[] seq = {Parser.AGE_SIZE, Parser.CAT_SIZE, Parser.CAT_SIZE,
        Parser.NCHILD_SIZE, Parser.REL_SIZE, Parser.WORK_SIZE, Parser.CAT_SIZE,
        Parser.CAT_SIZE, Parser.MEX_SIZE, Parser.MTH_SIZE};
        int classified = 0;
        while (it.hasNext()) {
            int[] example = it.next();
            int field = 0;
            int index = l;
            boolean testOK = true;
            while (testOK && field < Parser.FIELDS) {
                testOK &= matchRulePortion(hyp, example, index, 
                        index + seq[field]);
                index += seq[field];
                field++;
            }
            if (testOK) {classified++;}
        }
        return classified;
    }
    
    private boolean matchRulePortion(int[] hyp, int[] example, int l, int h) {
        int valid = 0;
        for(int i = l; i < h; i++) {
            valid |= hyp[i] & example[i];
        }
        return valid == 1;
    }
    
    private double max(double[] ftn) {
        double h = 0.0;
        for(int i = 0; i < ftn.length; i++) {
            h = Math.max(h, ftn[i]);
        }
        return r;
    }
    
    private ArrayList<int[]> rouletteSelection(int prop, double[] ftn, 
            ArrayList<int[]> population) {
        // Suma de todos los fitness
        double sum = 0.0;
        for(int i = 0; i < ftn.length; i++)
            sum += ftn[i];
        // Probabilidad de cada hipótesis
        double[] pr = new double[ftn.length];
        for(int i = 0; i < pr.length; i++) 
            pr[i] = ftn[i] / sum;
        ArrayList<Pair> prs = new ArrayList<Pair>(population.size());
    }
    
    public int[] go() {
        int[] finalHyp = new int[20];
        ArrayList<int[]> population = initPop();
        double[] ftn = fitness(population);
        while (max(ftn) < fitness_threshold) {
            // Crear nueva generación
            int prop = (int) ((1-r)*p); 
            ArrayList<int[]> Ps = rouletteSelection(prop, ftn, population);
        }
        return finalHyp;
    }
}
