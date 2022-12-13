import java.util.Scanner;

//Aqui fica todo o programa responsável por encontrar a inversa.

public class MatrizInversaAtravesDaAdjunta {

    static int N = 3;
    static int M = 3;

    // Função para obter o cofacto de A[p][q] n é
    // dimensão de A[][]
    static void getCofactor(int A[][], int temp[][], int p, int q, int n) {
        int i = 0, j = 0;

        // Loop para encontra cada elemento da matriz
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // Função para copiar apenas os elementos da matriz temporaria
                // que não estão em determinada linha e coluna
                if (row != p && col != q) {
                    temp[i][j++] = A[row][col];

                    // Verifica se A linha está preenchida, caso esteja aumenta o índice da linha
                    // e redefinir o índice da coluna
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    /*
     * Função recursiva para encontra o determinante da matriz.
     * n é dimensão de A[][].
     */
    static int determinant(int A[][], int n) {
        int D = 0; // Inicializar resultado

        // Base case : para verificar se a matriz contém um único elemento.
        if (n == 1)
            return A[0][0];

        int[][] temp = new int[N][N]; // Array temporario para armazenar os cofatores

        int sign = 1; // Para armazenar o multiplicador de sinal

        // Iterar cada elemento da primeira linha
        for (int f = 0; f < n; f++) {
            // Essa função serve para pegar o Cofator de A[0][f]
            getCofactor(A, temp, 0, f, n);
            D += sign * A[0][f] * determinant(temp, n - 1);

            sign = -sign;
        }
        return D;
    }

    // Função para obter a adjunta de A[N][N] dentro da adj[N][N].
    static void adjoint(int A[][], int[][] adj) {
        if (N == 1) {
            adj[0][0] = 1;
            return;
        }

        // Array temporario usado para armazenar o cofator da matriz de A[][]
        int sign = 1;
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                getCofactor(A, temp, i, j, N);

                sign = ((i + j) % 2 == 0) ? 1 : -1;

                // algoritimo para ir Trocando as linhas e colunas para obter a
                // transposta da matriz de cofatores
                adj[j][i] = (sign) * (determinant(temp, N - 1));
            }
        }
    }

    // algoritimo para calcula a inversa
    static boolean inverse(int A[][], float[][] inverse) {

        int det = determinant(A, N);
        if (det == 0) {
            System.out.print("o determinante é igual a 0. Logo, nao eh possivel encontrar a inversa");
            return false;
        }

        // algoritimo para encontrar a adjunta
        int[][] adj = new int[N][N];
        adjoint(A, adj);

        // algoritimo para encontra a inversa utilizando a formula definida na algebra
        // linear
        // que é: "inverse(A) = adj(A)/det(A)"
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                inverse[i][j] = adj[i][j] / (float) det;

        return true;
    }

    // // Função genérica para exibir a matriz, será usado para exibir
    // tanto a adjunta quanto a inversa.
    static void display(int A[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(A[i][j] + " ");
            System.out.println();
        }
    }

    static void display(float A[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%.1f ", A[i][j]);
            System.out.println();
        }
    }

    // esse é o Main onde o programa sera executado.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o numero de Linhas: ");
        N = sc.nextInt();
        System.out.print("Digite o numero de Colunas: ");
        M = sc.nextInt();
        int A[][] = new int[N][M];
        System.out.println("Insira os elementos da matriz: ");
        for (int i = 0; i < N; i++) {
            for (int k = 0; k < M; k++) {
                A[i][k] = sc.nextInt();

            }
        }

        int[][] adj = new int[N][M]; // Aqui fica armazenado a adjunta de A[][] na qual sera exibida.

        float[][] inv = new float[N][M]; // Aqui fica armazenado a inversa de A[][] na qual sera exibida.

        System.out.print("Matriz inserida :\n");
        display(A);

        if (N != M) {
            System.out.println("A matriz não é quadrada. Portanto, não tem como calcular o determinante nem a inversa");
        } else {
            System.out.println("\nDETERMINANTE: " + determinant(A, N));

            System.out.print("\nMatriz Adjunta :\n");
            adjoint(A, adj);
            display(adj);

            System.out.print("\nMatriz Inversa :\n");
            if (inverse(A, inv)) {
                display(inv);
            }
        }
    }
}
