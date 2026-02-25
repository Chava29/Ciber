package Parte2.Figuras;

public class Cuadrado extends FiguraGeometrica {

    private final double lado;

    public Cuadrado(double lado) {
        super("Cuadrado");
        validarPositivo(lado, "Lado");
        this.lado = lado;
    }

    @Override
    public double area() {
        return lado * lado;
    }

    @Override
    public double perimetro() {
        return 4 * lado;
    }
}