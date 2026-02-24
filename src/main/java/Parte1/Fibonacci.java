package Parte1;

// Sucesión de Fibonacci.Fibonacci - primeros 50 números
public class Fibonacci {

    public static void main(String[] args) {

        final int N = 50;   // cantidad de términos a imprimir

        long a = 1;         // primer término
        long b = 1;         // segundo término

        System.out.println("Primeros " + N + " números de Fibonacci.Fibonacci:");

        for (int i = 1; i <= N; i++) {

            // Imprime el término actual
            System.out.print(a);

            // Para no dejar coma al final
            if (i < N) {
                System.out.print(" ");
            }

            // Calcula el siguiente término
            long siguiente = a + b;
            a = b;
            b = siguiente;
        }

        System.out.println(); // salto de línea final
    }
}