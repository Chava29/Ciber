package Parte2.RegistroAlumnos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;

public class InterfazRegistro extends JFrame
{

        private final AlumnoLogica logica;
        private final AlumnoTabla tabla;

        private JTextField txtBoleta, txtNombre, txtApP, txtApM, txtGrupo;
        private JComboBox<Integer> cmbSemestre;
        private JComboBox<String> cmbTurno;
        private JTable table;

        private JButton btnAgregar, btnActualizar, btnEliminar, btnLimpiar;

        public InterfazRegistro(AlumnoLogica logica) {
            this.logica = logica;
            this.tabla = new AlumnoTabla(logica.obtenerTodos());

            setTitle("CECyT 9 - Registro de Alumnos (ArrayList)");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(900, 520);
            setLocationRelativeTo(null);

            initUI();
            refrescarTabla();
        }

        private void initUI() {
            JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
            panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Formulario (arriba)
            JPanel panelForm = new JPanel(new GridLayout(3, 4, 10, 10));

            txtBoleta = new JTextField();
            txtNombre = new JTextField();
            txtApP = new JTextField();
            txtApM = new JTextField();
            txtGrupo = new JTextField();

            cmbSemestre = new JComboBox<>(new Integer[]{1,2,3,4,5,6});
            cmbTurno = new JComboBox<>(new String[]{"Matutino", "Vespertino"});

            panelForm.add(new JLabel("Boleta:"));
            panelForm.add(txtBoleta);
            panelForm.add(new JLabel("Semestre:"));
            panelForm.add(cmbSemestre);

            panelForm.add(new JLabel("Nombre(s):"));
            panelForm.add(txtNombre);
            panelForm.add(new JLabel("Grupo:"));
            panelForm.add(txtGrupo);

            panelForm.add(new JLabel("Apellido Paterno:"));
            panelForm.add(txtApP);
            panelForm.add(new JLabel("Turno:"));
            panelForm.add(cmbTurno);

            panelForm.add(new JLabel("Apellido Materno:"));
            panelForm.add(txtApM);
            panelForm.add(new JLabel(""));
            panelForm.add(new JLabel(""));

            // Botones (centro arriba)
            JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            btnAgregar = new JButton("Agregar");
            btnActualizar = new JButton("Actualizar");
            btnEliminar = new JButton("Eliminar");
            btnLimpiar = new JButton("Limpiar");

            panelBotones.add(btnAgregar);
            panelBotones.add(btnActualizar);
            panelBotones.add(btnEliminar);
            panelBotones.add(btnLimpiar);

            JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
            panelNorte.add(panelForm, BorderLayout.CENTER);
            panelNorte.add(panelBotones, BorderLayout.SOUTH);

            // Tabla (abajo)
            table = new JTable(tabla);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scroll = new JScrollPane(table);

            // Eventos
            btnAgregar.addActionListener(e -> onAgregar());
            btnActualizar.addActionListener(e -> onActualizar());
            btnEliminar.addActionListener(e -> onEliminar());
            btnLimpiar.addActionListener(e -> limpiarFormulario());

            table.getSelectionModel().addListSelectionListener(this::onSeleccionTabla);

            // Layout
            panelPrincipal.add(panelNorte, BorderLayout.NORTH);
            panelPrincipal.add(scroll, BorderLayout.CENTER);

            setContentPane(panelPrincipal);
        }

        private void onAgregar() {
            Alumno alumno = construirAlumnoDesdeFormulario();
            if (alumno == null) return;

            boolean ok = logica.agregar(alumno);
            if (!ok) {
                JOptionPane.showMessageDialog(this,
                        "Ya existe un alumno con esa boleta.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            refrescarTabla();
            limpiarFormulario();
            JOptionPane.showMessageDialog(this, "Alumno agregado correctamente.");
        }

        private void onActualizar() {
            int fila = table.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un alumno en la tabla primero.");
                return;
            }

            Alumno nuevo = construirAlumnoDesdeFormulario();
            if (nuevo == null) return;

            boolean ok = logica.actualizarPorIndice(fila, nuevo);
            if (!ok) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo actualizar. Revisa que la boleta no esté repetida.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            refrescarTabla();
            JOptionPane.showMessageDialog(this, "Alumno actualizado.");
        }

        private void onEliminar() {
            int fila = table.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un alumno en la tabla primero.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Seguro que quieres eliminar el alumno seleccionado?",
                    "Confirmar", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = logica.eliminarPorIndice(fila);
            if (ok) {
                refrescarTabla();
                limpiarFormulario();
                JOptionPane.showMessageDialog(this, "Alumno eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
            }
        }

        private void onSeleccionTabla(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) return;
            int fila = table.getSelectedRow();
            if (fila < 0) return;

            Alumno a = logica.obtenerTodos().get(fila);

            txtBoleta.setText(a.getBoleta());
            txtNombre.setText(a.getNombre());
            txtApP.setText(a.getApellidoPaterno());
            txtApM.setText(a.getApellidoMaterno());
            txtGrupo.setText(a.getGrupo());
            cmbSemestre.setSelectedItem(a.getSemestre());
            cmbTurno.setSelectedItem(a.getTurno());
        }

        private Alumno construirAlumnoDesdeFormulario() {
            String boleta = txtBoleta.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apP = txtApP.getText().trim();
            String apM = txtApM.getText().trim();
            String grupo = txtGrupo.getText().trim();
            int semestre = (Integer) cmbSemestre.getSelectedItem();
            String turno = (String) cmbTurno.getSelectedItem();

            // Validaciones “normales” (sin throw)
            if (boleta.isEmpty() || nombre.isEmpty() || apP.isEmpty() || apM.isEmpty() || grupo.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Todos los campos deben estar llenos.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            // Boleta: solo dígitos (tú ajusta si tu profe pide otra regla)
            if (!boleta.matches("\\d+")) {
                JOptionPane.showMessageDialog(this,
                        "La boleta debe contener solo números.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            // Grupo: ejemplo simple (letras/números)
            if (!grupo.matches("[A-Za-z0-9]+")) {
                JOptionPane.showMessageDialog(this,
                        "El grupo solo debe contener letras y/o números (ej. 2IV7, 4IM2).",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return null;
            }

            return new Alumno(boleta, nombre, apP, apM, semestre, grupo.toUpperCase(), turno);
        }

        private void limpiarFormulario() {
            txtBoleta.setText("");
            txtNombre.setText("");
            txtApP.setText("");
            txtApM.setText("");
            txtGrupo.setText("");
            cmbSemestre.setSelectedIndex(0);
            cmbTurno.setSelectedIndex(0);
            table.clearSelection();
            txtBoleta.requestFocus();
        }

        private void refrescarTabla() {
            tabla.setData(logica.obtenerTodos());
        }
}

