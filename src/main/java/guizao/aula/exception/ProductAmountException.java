package guizao.aula.exception;

public class ProductAmountException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ProductAmountException(String productName) {
    super("Requested amount of " + productName + " not available");
  }
}
