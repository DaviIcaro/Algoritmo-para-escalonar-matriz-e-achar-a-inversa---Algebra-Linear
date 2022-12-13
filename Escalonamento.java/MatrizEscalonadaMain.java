import java.util.Scanner;

//Aqui é o "MAIN" onde o programa será executado.

class MatrizEscalonadaMain {
    public static void main(String[] args) {

        int column = 0;
        int row = 0;
        int next = 1;

        MatrizEscalonada rref = new MatrizEscalonada();
        while (next == 1) {
            Scanner scInt = new Scanner(System.in);
            System.out.print("Insira a quantidade de Colunas: ");
            column = scInt.nextInt();
            System.out.print("Insira a quantidade de Linhas: ");
            row = scInt.nextInt();

            double[][] matrix = new double[row][column];
            System.out.println("Insira os elementos da Matriz:");
            for (int i = 0; i < row; i++) {
                Scanner sc = new Scanner(System.in);
                String AColumn = sc.nextLine();
                // Separar os elementos com um espaço pra ficar melhor de ler.
                String[] columnArr = AColumn.split(" ");
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = Double.parseDouble(columnArr[j]);
                }
            }
            System.out.println();

            // Laço de repetição para chama os metodos, pegar os dados, a quantidade de
            // colunas e linhas e relizar
            // as operações através da matriz inserida.
            for (int i = 0; i < row; i++) {
                // primeira operação
                rref.EntradaDoCursor(matrix, i);
                // segunda operação
                rref.divideM(matrix, i);
                // terceira operação
                rref.eliminateM(matrix, i);
                // quarta operação para exibir as operações da matriz.
                rref.printM(matrix);

            }
            // pra parar o laço de repetição.
            next = scInt.nextInt();

        }
    }
}