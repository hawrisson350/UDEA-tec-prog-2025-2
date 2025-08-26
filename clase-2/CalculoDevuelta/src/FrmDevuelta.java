import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class FrmDevuelta extends JFrame {

    // Variable Global
    private int[] denominacion = new int[] { 100000, 50000, 20000, 10000, 5000, 2000, 1000, 500, 200, 100, 50 };
    private int[] existencia = new int[denominacion.length];
    private JComboBox cmbDenominacion;
    private JTextField txtExistencia, txtDevuelta;
    private String[] encabezados = new String[] { "Cantidad", "Presentacion", "Denominación" };
    private JTable tblDevuelta;

    // Método Constructor
    public FrmDevuelta() {
        setTitle("Calculo Devuelta");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Agregar una etiqueta

        JLabel lblDenominacion = new JLabel("Denominación: ");
        lblDenominacion.setBounds(100, 10, 100, 25);
        getContentPane().add(lblDenominacion);

        // Lista desplegable
        cmbDenominacion = new JComboBox();
        cmbDenominacion.setBounds(200, 10, 150, 25);
        getContentPane().add(cmbDenominacion);

        // Crear un Modelo de datos para la lista desplegable
        String strDenominacion[] = new String[denominacion.length];
        for (int i = 0; i < denominacion.length; i++) {
            strDenominacion[i] = String.valueOf(denominacion[i]);
        }
        cmbDenominacion.setModel(new DefaultComboBoxModel(strDenominacion));

        // Agregar un boton
        JButton btnActualizarExistencia = new JButton("Actualizar Existencia");
        btnActualizarExistencia.setBounds(10, 40, 180, 25);
        getContentPane().add(btnActualizarExistencia);

        // Agregar una caja de texto
        txtExistencia = new JTextField();
        txtExistencia.setBounds(200, 40, 150, 25);
        getContentPane().add(txtExistencia);

        // Agregar los eventos para actualizar las Existencias
        cmbDenominacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarExistencia();
            }
        });

        btnActualizarExistencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarExistencia();
            }
        });

        // Separador
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setBounds(0, 80, 500, 10);
        getContentPane().add(separator);

        // Agregar elementos para leer el valor a devolver
        JLabel lblDevuelta = new JLabel("Valor a Devolver: ");
        lblDevuelta.setBounds(100, 90, 100, 25);
        getContentPane().add(lblDevuelta);

        txtDevuelta = new JTextField();
        txtDevuelta.setBounds(200, 90, 150, 25);
        getContentPane().add(txtDevuelta);

        JButton btnDevuelta = new JButton("Calcular Devuelta");
        btnDevuelta.setBounds(100, 130, 250, 25);
        getContentPane().add(btnDevuelta);

        // Agregar rejilla de datos
        tblDevuelta = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblDevuelta);
        scrollPane.setBounds(10, 170, 460, 200);
        getContentPane().add(scrollPane);

        // Asignar modelo a la rejilla
        DefaultTableModel dtm = new DefaultTableModel(null, encabezados);
        tblDevuelta.setModel(dtm);

        btnDevuelta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularDevuelta();
            }
        });

    }

    private void consultarExistencia() {
        int existenciaActual = existencia[cmbDenominacion.getSelectedIndex()];
        txtExistencia.setText(String.valueOf(existenciaActual));
    }

    private void actualizarExistencia() {
        int existenciaActual = Integer.parseInt(txtExistencia.getText());
        existencia[cmbDenominacion.getSelectedIndex()] = existenciaActual;
        System.out.println("Existencia actualizada: ");
        for (int i = 0; i < denominacion.length; i++) {
            System.out.println("Denominación: " + denominacion[i] + ", Existencia: " + existencia[i]);
        }
    }

    private void calcularDevuelta() {

        int[] devuelta = new int[denominacion.length];

        int valorDevuelta = Integer.parseInt(txtDevuelta.getText());
        int i = 0;
        int totalFilas = 0;
        while (valorDevuelta > 0 && i < denominacion.length) {
            int cantidadNecesaria = (int) (valorDevuelta / denominacion[i]);
            devuelta[i] = existencia[i] >= cantidadNecesaria ? cantidadNecesaria : existencia[i];

            // if (existencia[i] >= cantidadNecesaria) {
            // devuelta[i] = cantidadNecesaria;
            // } else {
            // devuelta[i] = existencia[i];
            // }
            if (devuelta[i] > 0) {
                valorDevuelta -= devuelta[i] * denominacion[i];
                totalFilas++;
            }
            i++;
        }

        String[][] datos = new String[totalFilas][encabezados.length];
        totalFilas = 0;
        for (i = 0, i = 0; i < devuelta.length; i++) {
            if (devuelta[i] > 0) {
                datos[totalFilas][0] = String.valueOf(devuelta[i]);
                datos[totalFilas][1] = denominacion[i] < 1000 ? "Moneda" : "Billete";
                datos[totalFilas][2] = String.valueOf(denominacion[i]);
                totalFilas++;
            }
        }

        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tblDevuelta.setModel(dtm);

        if(valorDevuelta > 0) {
            JOptionPane.showMessageDialog(null, "Queda pendiente $" + valorDevuelta+" por devolver");
        }

    }
}
