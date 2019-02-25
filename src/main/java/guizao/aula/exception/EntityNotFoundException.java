package guizao.aula.exception;

public class EntityNotFoundException extends NullPointerException {

  private static final long serialVersionUID = 1L;

  public EntityNotFoundException (String entityName, String id) {
    super("Entity " + entityName + " was not founded by Id: " + id);
  }
}
