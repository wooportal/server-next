package app.wooportal.server.core.error.exception;

public class XmppException extends BaseException {

  private static final long serialVersionUID = 1L;

  public XmppException(String message, Object... params) {
      super(message, params);
    }
}
