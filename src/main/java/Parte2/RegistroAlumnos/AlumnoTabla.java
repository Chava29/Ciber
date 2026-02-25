package Parte2.RegistroAlumnos;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AlumnoTabla extends AbstractTableModel
{


        private final String[] columnas = {"Boleta", "Nombre completo", "Semestre", "Grupo", "Turno"};
        private List<Alumno> data;

        public AlumnoTabla(List<Alumno> data) {
            this.data = data;
        }

        public void setData(List<Alumno> data) {
            this.data = data;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public int getColumnCount() {
            return columnas.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnas[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Alumno a = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> a.getBoleta();
                case 1 -> a.getNombreCompleto();
                case 2 -> a.getSemestre();
                case 3 -> a.getGrupo();
                case 4 -> a.getTurno();
                default -> "";
            };
        }
}
