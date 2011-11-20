package genetico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lorenzo Fundaró <lorenzofundaro [at] yahoo.com>
 */
public class Parser {

    public String filePath;
    public static final int MAX_AGE  = 50;
    public static final int MIN_AGE  = 15;
    public static final int MIN_CHILD = 0;
    public static final int MAX_CHILD = 5;
    public static final int REP_SIZE = 36;
    public static final int AGE_SIZE = 7;
    public static final int CAT_SIZE = 4;
    public static final int NCHILD_SIZE = 6;
    public static final int REL_SIZE = 2;
    public static final int WORK_SIZE = 2;
    public static final int MEX_SIZE = 2;
    public static final int MTH_SIZE = 1;

    public Parser(String fname) {
        filePath = fname;
    }

    public ArrayList<int[]> go() {
        ArrayList<int[]> pool = new ArrayList<int[]>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String nextLine;
            String[] tokenized;
            int[] ex;
            int tmp;
            int maxIndex;

            while ((nextLine = in.readLine()) != null) {
                maxIndex = 0;
                tokenized = nextLine.split(",");
                ex = new int[REP_SIZE];
                init(ex);
                // Edad de la mujer
                tmp = Integer.parseInt(tokenized[0]);
                maxIndex = calcAge(ex, tmp, maxIndex);
                // Educación de la mujer
                tmp = Integer.parseInt(tokenized[1]);
                maxIndex = calCat(ex, tmp, maxIndex);
                // Educación del esposo
                tmp = Integer.parseInt(tokenized[2]);
                maxIndex = calCat(ex, tmp, maxIndex);
                // Número de hijos
                tmp = Integer.parseInt(tokenized[3]);
                maxIndex = numChildren(ex, tmp, maxIndex);
                // Religión de la mujer
                tmp = Integer.parseInt(tokenized[4]);
                maxIndex = calRel(ex, tmp, maxIndex);
                // Madre en situación de trabajo ?
                tmp = Integer.parseInt(tokenized[5]);
                maxIndex = calWorking(ex, tmp, maxIndex);
                // Ocupación del esposo
                tmp = Integer.parseInt(tokenized[6]);
                maxIndex = calCat(ex, tmp, maxIndex);
                // Estándar de vida
                tmp = Integer.parseInt(tokenized[7]);
                maxIndex = calCat(ex, tmp, maxIndex);
                // Exposición al ambiente
                tmp = Integer.parseInt(tokenized[8]);
                maxIndex = calMediaExp(ex, tmp, maxIndex);
                // Método anticonceptivo
                tmp = Integer.parseInt(tokenized[9]);
                ex[maxIndex] = tmp;
                pool.add(ex);
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("No se pudo encontrar el archivo: " + filePath);
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e);
        }
        return pool;
    }

    private void init(int[] ex) {
        for (int i = 0; i < ex.length; i++) {
            ex[i] = 0;
        }
    }

    public static int calRel(int[] ex, int val, int maxIndex) {
        if (val == 1) // Islam
        {
            ex[maxIndex] = 1;
        } else // No-Islam
        {
            ex[maxIndex + 1] = 1;
        }

        return maxIndex + REL_SIZE;
    }

    public static int calWorking(int[] ex, int val, int maxIndex) {
        if (val == 0) // YES
        {
            ex[maxIndex] = 1;
        } else // NO
        {
            ex[maxIndex + 1] = 1;
        }

        return maxIndex + WORK_SIZE;
    }

    public static int calMediaExp(int[] ex, int val, int maxIndex) {
        if (val == 0) // Good
        {
            ex[maxIndex] = 1;
        } else // Not good
        {
            ex[maxIndex + 1] = 1;
        }

        return maxIndex + MEX_SIZE;
    }

    public static int numChildren(int[] ex, int nc, int maxIndex) {
        if (nc == 0) {
            ex[maxIndex] = 1;
        } else if (nc == 1) {
            ex[maxIndex + 1] = 1;
        } else if (nc == 2) {
            ex[maxIndex + 2] = 1;
        } else if (nc == 3) {
            ex[maxIndex + 3] = 1;
        } else if (nc == 4) {
            ex[maxIndex + 4] = 1;
        } else // Para personas con hijos >= 5.
        {
            ex[maxIndex + 5] = 1;
        }

        return maxIndex + NCHILD_SIZE;
    }

    public static int calCat(int[] ex, int level, int maxIndex) {
        if (level == 1) {
            ex[maxIndex] = 1;
        } else if (level == 2) {
            ex[maxIndex + 1] = 1;
        } else if (level == 3) {
            ex[maxIndex + 2] = 1;
        } else {
            ex[maxIndex + 3] = 1;
        }

        return maxIndex + CAT_SIZE;
    }

    public static int calcAge(int[] ex, int age, int maxIndex) {
        if (age >= 15 && age <= 20) {
            ex[maxIndex] = 1;
        } else if (age > 20 && age <= 25) {
            ex[maxIndex + 1] = 1;
        } else if (age > 25 && age <= 30) {
            ex[maxIndex + 2] = 1;
        } else if (age > 30 && age <= 35) {
            ex[maxIndex + 3] = 1;
        } else if (age > 35 && age <= 40) {
            ex[maxIndex + 4] = 1;
        } else if (age > 40 && age <= 45) {
            ex[maxIndex + 5] = 1;
        } else // age > 45 && age <= 50
        {
            ex[maxIndex + 6] = 1;
        }

        return maxIndex + AGE_SIZE;  // Máximo índice que utiliza esta función
    }

    public void test(int[] test) {
        System.out.println("Edad");
        for (int i = 0; i < 7; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Educación esposa");
        for (int i = 7; i < 11; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Educación del esposo");
        for (int i = 11; i < 15; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Número de hijos");
        for (int i = 15; i < 21; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Religión de la esposa");
        for (int i = 21; i < 23; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Situación de trabajo de la esposa");
        for (int i = 23; i < 25; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Ocupación del marido");
        for (int i = 25; i < 29; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Estándar de vida");
        for (int i = 29; i < 33; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Exposición al ambiente");
        for (int i = 33; i < 35; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
        System.out.println("Método anticonceptivo");
        for (int i = 35; i < 36; i++) {
            System.out.print(test[i]);
        }
        System.out.println("");
    }
}
