package Parte2.RegistroAlumnos;

import javax.swing.SwingUtilities;

public class RegistroAlumnos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlumnoLogica logica = new AlumnoLogica();
            InterfazRegistro frame = new InterfazRegistro(logica);
            frame.setVisible(true);
        });
    }
}