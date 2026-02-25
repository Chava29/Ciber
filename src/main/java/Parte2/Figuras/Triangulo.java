package Parte2.Figuras;

public class Triangulo extends FiguraGeometrica {

    private final double a, b, c;

    public Triangulo(double a, double b, double c) {
        super("Triángulo");
        validarPositivo(a, "Lado A");
        validarPositivo(b, "Lado B");
        validarPositivo(c, "Lado C");

        // Desigualdad triangular
        if (a + b <= c || a + c <= b || b + c <= a) {

            //throw new, es como un try, catch, pero más formal. Evita que el programa siga con datos inválidos.
            throw new IllegalArgumentException("Los lados no forman un triángulo válido.");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        // Fórmula de Herón
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimetro() {
        return a + b + c;
    }
}
