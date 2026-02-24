package Parte1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Factorial {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Cálculo de Factorial ===");

        int n = leerEnteroNoNegativo(sc, "Ingresa un número entero (x>= 0): ");

        // 20! cabe en long, 21! ya se desborda
        if (n > 20) {
            System.out.println("Advertencia: " + n + "! excede el rango de un tipo long.");
            System.out.println("Intenta con un número <= 20 para obtener un resultado exacto en long.");
            sc.close();
            return;
        }

        long factorial = 1;

        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        System.out.println(n + "! = " + factorial);

        sc.close();
    }

    private static int leerEnteroNoNegativo(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                if (valor < 0) {
                    System.out.println("Error: el factorial no está definido para números negativos.");
                } else {
                    return valor;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debes ingresar un número entero.");
                sc.next(); // limpiar entrada incorrecta
            }
        }
    }
}