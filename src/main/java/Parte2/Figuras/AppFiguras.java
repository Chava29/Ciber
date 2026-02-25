package Parte2.Figuras;

import java.util.Scanner;

public class AppFiguras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== FIGURAS GEOMÉTRICAS (POO + Herencia) ===");
            System.out.println("1) Cuadrado");
            System.out.println("2) Rectángulo");
            System.out.println("3) Triángulo");
            System.out.println("4) Círculo");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");

            opcion = leerEntero(sc);

            try {
                FiguraGeometrica figura = null;

                switch (opcion) {
                    case 1 -> {
                        double lado = leerDoublePositivo(sc, "Lado: ");
                        figura = new Cuadrado(lado);
                    }
                    case 2 -> {
                        double base = leerDoublePositivo(sc, "Base: ");
                        double altura = leerDoublePositivo(sc, "Altura: ");
                        figura = new Rectangulo(base, altura);
                    }
                    case 3 -> {
                        double a = leerDoublePositivo(sc, "Lado A: ");
                        double b = leerDoublePositivo(sc, "Lado B: ");
                        double c = leerDoublePositivo(sc, "Lado C: ");
                        figura = new Triangulo(a, b, c);
                    }
                    case 4 -> {
                        double radio = leerDoublePositivo(sc, "Radio: ");
                        figura = new Circulo(radio);
                    }
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción inválida.");
                }

                if (figura != null) {
                    System.out.println("\n--- RESULTADOS ---");
                    System.out.println("Figura: " + figura.getNombre());
                    System.out.printf("Área: %.6f%n", figura.area());
                    System.out.printf("Perímetro: %.6f%n", figura.perimetro());
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);

        sc.close();
    }

    // ===== Lectura robusta (validación) =====

    private static int leerEntero(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingresa un entero: ");
            }
        }
    }

    private static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String s = sc.nextLine().trim().replace(',', '.'); // acepta coma o punto
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número (ej. 2, 3.5, -1).");
            }
        }
    }

    private static double leerDoublePositivo(Scanner sc, String mensaje) {
        while (true) {
            double v = leerDouble(sc, mensaje);
            if (v > 0) return v;
            System.out.println("Debe ser > 0.");
        }
    }
}
