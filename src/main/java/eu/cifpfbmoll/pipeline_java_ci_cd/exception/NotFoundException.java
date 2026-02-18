package eu.cifpfbmoll.pipeline_java_ci_cd.exception;

public class NotFoundException extends RuntimeException {
  public NotFoundException(String message) {
    super(message);
  }
}
