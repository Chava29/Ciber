package Parte1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class F_Cuadratica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Resolución de ecuación: ax^2 + bx + c = 0 ===");

        double a = leerDouble(sc, "Ingresa a: ");
        double b = leerDouble(sc, "Ingresa b: ");
        double c = leerDouble(sc, "Ingresa c: ");

        // Tolerancia para comparar con 0 (por temas de decimales)
        final double EPS = 1e-12;

        // Caso especial: si a = 0, ya no es cuadrática
        if (Math.abs(a) < EPS) {
            resolverLineal(b, c, EPS);
            return;
        }

        // Discriminante
        double discriminante = (b * b) - (4 * a * c);

        System.out.printf("%nDiscriminante (b^2 - 4ac) = %.6f%n", discriminante);

        if (discriminante > EPS) {
            // Dos soluciones reales distintas
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);

            System.out.println("Resultado: Dos soluciones reales.");
            System.out.printf("x1 = %.6f%n", x1);
            System.out.printf("x2 = %.6f%n", x2);

        } else if (Math.abs(discriminante) <= EPS) {
            double x = (-b) / (2 * a);

            System.out.println("Resultado: Una solución real (raíz doble).");
            System.out.printf("x = %.6f%n", x);

        } else {
            // Ninguna solución real
            System.out.println("Resultado: Ninguna solución en los números reales (raíces complejas).");
        }

        sc.close();
    }

    // Lee un double con validación, no deja que el programa truene si meten texto
    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debes ingresar un número (ej. 2, -3.5, 0).");
                sc.next(); // limpiar la entrada incorrecta
            }
        }
    }

    // Resuelve bx + c = 0 (si a = 0)
    private static void resolverLineal(double b, double c, double EPS) {
        System.out.println("\nNota: Como a = 0, la ecuación NO es cuadrática. Se analiza como lineal: bx + c = 0");

        if (Math.abs(b) < EPS) {
            if (Math.abs(c) < EPS) {
                System.out.println("Resultado: Infinitas soluciones (0 = 0).");
            } else {
                System.out.println("Resultado: Ninguna solución (contradicción, por ejemplo 5 = 0).");
            }
        } else {
            double x = (-c) / b;
            System.out.println("Resultado: Una solución real (ecuación lineal).");
            System.out.printf("x = %.6f%n", x);
        }
    }
}