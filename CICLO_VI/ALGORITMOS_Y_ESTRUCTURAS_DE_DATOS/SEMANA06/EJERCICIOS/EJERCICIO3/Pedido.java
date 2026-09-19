public class Pedido {
  private int numeroPedido;
  private String cliente;
  private int cantidadPanes;

  public Pedido(int numeroPedido, String cliente, int cantidadPanes) {
    this.numeroPedido = numeroPedido;
    this.cliente = cliente;
    this.cantidadPanes = cantidadPanes;
  }

  public int getNumeroPedido() {
    return numeroPedido;
  }

  public String getCliente() {
    return cliente;
  }

  public int getCantidadPanes() {
    return cantidadPanes;
  }

  @Override
  public String toString() {
    return "[Pedido #" + numeroPedido + "] Cliente: " + cliente + " - Cantidad de panes: " + cantidadPanes;
  }
}
