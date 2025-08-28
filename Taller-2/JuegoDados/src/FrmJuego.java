import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FrmJuego extends JFrame {

    private Dado dado1 , dado2;
    private Random r;
    JLabel lblDado2, lblDado1;

    public FrmJuego() {
        setTitle("Juego de Dados");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        dado1 = new Dado();
        dado2 = new Dado();
        r = new Random();

        lblDado1 = new JLabel();
        String archivoImagen = "imgs/2.jpg";
        ImageIcon imgDado = new ImageIcon(getClass().getResource(archivoImagen));
        lblDado1.setIcon(imgDado);
        lblDado1.setBounds(10, 10, imgDado.getIconWidth(), imgDado.getIconHeight());
        getContentPane().add(lblDado1);

        lblDado2 = new JLabel();
        String archivoImagen2 = "imgs/5.jpg";
        ImageIcon imgDado2 = new ImageIcon(getClass().getResource(archivoImagen2));
        lblDado2.setIcon(imgDado2);
        lblDado2.setBounds(20 + imgDado.getIconWidth(), 10, imgDado2.getIconWidth(), imgDado2.getIconHeight());
        getContentPane().add(lblDado2);

        JLabel lblTituloLanzamientos = new JLabel("Lanzamientos");
        lblTituloLanzamientos.setBounds(30 + 2 * imgDado.getIconWidth(), 10, 100, 25);
        lblTituloLanzamientos.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTituloLanzamientos);

        JLabel lblTituloCenas = new JLabel("Cenas");
        lblTituloCenas.setBounds(140 + 2 * imgDado.getIconWidth(), 10, 100, 25);
        lblTituloCenas.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(lblTituloCenas);

        JLabel lblLanzamientos = new JLabel("0");
        lblLanzamientos.setBounds(30 + 2 * imgDado.getIconWidth(), 40, 100, 100);
        lblLanzamientos.setFont(new Font("Tahoma", 1, 72));
        lblLanzamientos.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLanzamientos.setBackground(new Color(0, 0, 0));
        lblLanzamientos.setForeground(new Color(50, 255, 255));
        lblLanzamientos.setOpaque(true);
        getContentPane().add(lblLanzamientos);

        JLabel lblCenas = new JLabel("0");
        lblCenas.setBounds(140 + 2 * imgDado.getIconWidth(), 40, 100, 100);
        lblCenas.setFont(new Font("Tahoma", 1, 72));
        lblCenas.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCenas.setBackground(new Color(0, 0, 0));
        lblCenas.setForeground(new Color(50, 255, 255));
        lblCenas.setOpaque(true);
        getContentPane().add(lblCenas);

        JButton btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(10, 20 + imgDado.getIconHeight(), 100, 25);
        getContentPane().add(btnIniciar);

        JButton btnLanzar = new JButton("Lanzar");
        btnLanzar.setBounds(10, 60 + imgDado.getIconHeight(), 100, 25);
        getContentPane().add(btnLanzar);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciar();
            }
        });

        btnLanzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lanzar();
            }
        });
    }

    private void iniciar() {

    }

    private void lanzar() {
        dado1.lanzar(r);
        dado2.lanzar(r);
        dado1.mostrar(lblDado1);
        dado2.mostrar(lblDado2);
    }

}
