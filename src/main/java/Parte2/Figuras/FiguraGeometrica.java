package Parte2.Figuras;

public abstract class FiguraGeometrica {

    private final String nombre;

    public FiguraGeometrica(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Métodos que TODA figura debe implementar
    public abstract double area();
    public abstract double perimetro();

    // Validación común para reutilizar en todas las figuras
    protected static void validarPositivo(double valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser mayor que 0.");
        }
    }
}