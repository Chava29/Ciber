package Parte2.RegistroAlumnos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlumnoLogica
{

        private final ArrayList<Alumno> alumnos = new ArrayList<>();

        public List<Alumno> obtenerTodos() {
            return Collections.unmodifiableList(alumnos);
        }

        public boolean existeBoleta(String boleta) {
            for (Alumno a : alumnos) {
                if (a.getBoleta().equalsIgnoreCase(boleta)) return true;
            }
            return false;
        }

        public boolean agregar(Alumno alumno) {
            if (existeBoleta(alumno.getBoleta())) return false;
            alumnos.add(alumno);
            return true;
        }

        public boolean actualizarPorIndice(int index, Alumno nuevo) {
            if (index < 0 || index >= alumnos.size()) return false;

            // Si la boleta cambia, validar que no choque con otra
            String boletaNueva = nuevo.getBoleta();
            for (int i = 0; i < alumnos.size(); i++) {
                if (i != index && alumnos.get(i).getBoleta().equalsIgnoreCase(boletaNueva)) {
                    return false;
                }
            }

            alumnos.set(index, nuevo);
            return true;
        }

        public boolean eliminarPorIndice(int index) {
            if (index < 0 || index >= alumnos.size()) return false;
            alumnos.remove(index);
            return true;
        }
}

