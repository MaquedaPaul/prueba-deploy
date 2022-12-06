package exceptions;

public class PasswordInseguraException extends RuntimeException {
  public PasswordInseguraException(String message) {
    super(message);
  }
}
