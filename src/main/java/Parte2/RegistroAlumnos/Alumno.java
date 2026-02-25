package Parte2.RegistroAlumnos;

public class Alumno {
    private String boleta;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int semestre;
    private String grupo;
    private String turno; // Matutino / Vespertino

    public Alumno(String boleta, String nombre, String apellidoPaterno, String apellidoMaterno,
                  int semestre, String grupo, String turno) {
        this.boleta = boleta;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.semestre = semestre;
        this.grupo = grupo;
        this.turno = turno;
    }

    public String getBoleta() { return boleta; }
    public void setBoleta(String boleta) { this.boleta = boleta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public int getSemestre() { return semestre; }
    public void setSemestre(int semestre) { this.semestre = semestre; }

    public String getGrupo() { return grupo; }
    public void setGrupo(String grupo) { this.grupo = grupo; }

    public String getTurno() { return turno; }
    public void setTurno(String turno) { this.turno = turno; }

    public String getNombreCompleto() {
        return nombre + " " + apellidoPaterno + " " + apellidoMaterno;
    }
}
