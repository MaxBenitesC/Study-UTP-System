public class Main {
      public static void main(String[] args) {
          double[] ventas = new double[10];
          int cantidad = 5;

          ventas[0] = 250.00;
          ventas[1] = 180.50;
          ventas[2] = 320.00;
          ventas[3] = 150.00;
          ventas[4] = 400.00;

          System.out.println("VENTAS INICIALES");
          mostrarVentas(ventas, cantidad);

          cantidad = insertarVenta(ventas, cantidad, 2, 275.50);

          actualizarVenta(ventas, cantidad, 3, 350.00);

          cantidad = eliminarVenta(ventas, cantidad, 1);

          System.out.println("\nVENTAS FINALES");
          mostrarVentas(ventas, cantidad);
      }

      public static void mostrarVentas(double[] ventas, int cantidad) {
          for (int i = 0; i < cantidad; i++) {
              System.out.printf("Posición %d: S/ %.2f%n", i, ventas[i]);
          }
      }

      public static int insertarVenta(double[] ventas, int cantidad, int posicion, double nuevaVenta) {
          if (cantidad >= ventas.length) {
              System.out.println("No hay espacio disponible.");
              return cantidad;
          }

          if (posicion < 0 || posicion > cantidad) {
              System.out.println("Posición inválida.");
              return cantidad;
          }

          for (int i = cantidad; i > posicion; i--) {
              ventas[i] = ventas[i - 1];
          }

          ventas[posicion] = nuevaVenta;
          cantidad++;

          return cantidad;
      }

      public static void actualizarVenta(double[] ventas, int cantidad, int posicion, double nuevoValor) {
          if (posicion < 0 || posicion >= cantidad) {
              System.out.println("Posición inválida.");
              return;
          }

          ventas[posicion] = nuevoValor;
      }

      public static int eliminarVenta(double[] ventas, int cantidad, int posicion) {
          if (posicion < 0 || posicion >= cantidad) {
              System.out.println("Posición inválida.");
              return cantidad;
          }

          for (int i = posicion; i < cantidad - 1; i++) {
              ventas[i] = ventas[i + 1];
          }

          cantidad--;

          return cantidad;
      }
  }
