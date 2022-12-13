//Fiz o programa orientado a objetos, por tanto essa classe
//é responsavel por ter todo o algoritimo pra escalonar a matriz.
class MatrizEscalonada {
    int column = 0;
    int row = 0;

    // pega a entrada e realiza a operação pelo teorema
    public void EntradaDoCursor(double[][] matrix, int currRow) {
        if (currRow < matrix[currRow].length) {
            for (int i = currRow; i < matrix.length - 1; i++) {
                if (matrix[i][currRow] == 0 && matrix[i + 1][currRow] != 0) {
                    for (int j = 0; j < matrix[currRow].length; j++) {
                        double temp = matrix[i][j];
                        matrix[i][j] = matrix[i + 1][j];
                        matrix[i + 1][j] = temp;
                    }
                    break;
                }
            }

            // verificar os elementos.
            if (matrix[currRow][currRow] == 0) {
                int tempRow = currRow;
                for (int i = currRow + 1; i < matrix[currRow].length; i++) {
                    boolean a = false;
                    for (int j = currRow; j < matrix.length; j++) {

                        if (matrix[j][i] != 0.0) {
                            tempRow = j;
                            a = true;
                            break;
                        }
                    }
                    if (a) {
                        break;
                    }
                }

                for (int i = 0; i < matrix[currRow].length; i++) {
                    double temp = matrix[tempRow][i];
                    matrix[tempRow][i] = matrix[currRow][i];
                    matrix[currRow][i] = temp;
                }
            }
        }
    }

    // Algoritimo feito para exibir a matriz.
    public void printM(double[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%.2f", matrix[i][j]);
                System.out.print("\t");

            }
            System.out.println();
        }
        System.out.println();
    }

    // algoritimo utilizando as operações permitidas para realizar as divisões, caso
    // seja necessário.
    public void divideM(double[][] matrix, int currRow) {
        if (currRow < matrix[currRow].length) {
            if (matrix[currRow][currRow] != 0) {
                double temp = matrix[currRow][currRow];

                for (int i = currRow; i < matrix[currRow].length; i++) {
                    if (matrix[currRow][i] != 0) {
                        matrix[currRow][i] /= temp;
                    }
                }
            } else {
                for (int i = currRow; i < matrix[currRow].length - 1; i++) {
                    if (matrix[currRow][i + 1] != 0) {
                        double temp = matrix[currRow][i + 1];

                        for (int j = i + 1; j < matrix[currRow].length; j++) {
                            matrix[currRow][j] /= temp;
                        }
                        break;
                    }
                }
            }
        }
    }

    // mais operações
    public void eliminateM(double[][] matrix, int currRow) {
        if (currRow < matrix[currRow].length) {
            if (matrix[currRow][currRow] != 0) {

                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[i][currRow] != 0 && i != currRow) {

                        double temp = matrix[i][currRow];
                        for (int j = currRow; j < matrix[currRow].length; j++) {
                            matrix[i][j] -= matrix[currRow][j] * temp;
                        }

                    }
                }
            } else {
                int tempC = 0;
                boolean a = false;
                for (int i = currRow; i < matrix[currRow].length; i++) {
                    if (matrix[currRow][i] != 0) {
                        tempC = i;
                        a = true;
                        break;
                    }
                }
                if (a) {
                    for (int i = 0; i < matrix.length; i++) {
                        if (matrix[i][tempC] != 0 && i != currRow) {
                            // System.out.println(matrix[i + 1][currRow]);
                            double temp = matrix[i][tempC];
                            for (int j = tempC; j < matrix[currRow].length; j++) {
                                matrix[i][j] -= matrix[currRow][j] * temp;

                            }
                        }
                    }
                }
            }
        }
    }
}