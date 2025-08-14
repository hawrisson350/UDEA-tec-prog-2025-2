import java.util.Scanner;

public class App {
    public static void main(String[] args)  {
        System.out.println("Programa para realizar calculos de una ecuación lineal");
        
        //Crea objeto para la lectura de datos de entrada
        Scanner sc = new Scanner(System.in);

        //Lectura de datos de entrada
        System.out.print("Ingrese x1: ");
        double x1 = sc.nextDouble();
        System.out.print("Ingrese y1: ");
        double y1 = sc.nextDouble();
        System.out.print("Ingrese x2: ");
        double x2 = sc.nextDouble();
        System.out.print("Ingrese y2: ");
        double y2 = sc.nextDouble();

        double dx = x2 - x1;
        double dy = y2 - y1;
        double pendiente = dy / dx;
        double distancia = Math.sqrt( Math.pow(dx, 2) + Math.pow(dy, 2) );
        System.out.println("Distancia entre los puntos : " + distancia);
        double b = y2 - pendiente * x2;
        System.out.println("Ecuación de la recta: y = " + pendiente + "x + (" + b + ")");
    }
}
