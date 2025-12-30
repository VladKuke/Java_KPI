import java.util.Random;

public class Lab1Variant15 {

    public static void main(String[] args) {
        final int ROWS = 4;
        final int COLS = 4;
        final byte CONSTANT_A = 2;

        byte[][] matrixB = new byte[ROWS][COLS];
        byte[][] matrixC = new byte[ROWS][COLS];

        Random random = new Random();
        System.out.println("Matrix B (Initial):");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                matrixB[i][j] = (byte) (random.nextInt(20) - 10);
                System.out.print(matrixB[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("------------------------------");

        try {
            System.out.println("Multiplying Matrix B by constant a = " + CONSTANT_A + "...");

            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    matrixC[i][j] = (byte) (CONSTANT_A * matrixB[i][j]);
                }
            }

            System.out.println("Matrix C (Result):");
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    System.out.print(matrixC[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("------------------------------");

            long finalSum = 0;

            for (int i = 0; i < ROWS; i++) {
                byte extrema = matrixC[i][0];

                if (i % 2 == 0) {
                    for (int j = 1; j < COLS; j++) {
                        if (matrixC[i][j] > extrema) {
                            extrema = matrixC[i][j];
                        }
                    }
                    System.out.println("Row " + i + " (Even) -> Max: " + extrema);
                } else {
                    for (int j = 1; j < COLS; j++) {
                        if (matrixC[i][j] < extrema) {
                            extrema = matrixC[i][j];
                        }
                    }
                    System.out.println("Row " + i + " (Odd)  -> Min: " + extrema);
                }

                finalSum += extrema;
            }

            System.out.println("------------------------------");
            System.out.println("Final Result (Sum of extremas): " + finalSum);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}