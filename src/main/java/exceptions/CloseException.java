package exceptions;

import java.io.IOException;

public class CloseException extends IOException {
    public CloseException(String message) {
        super(message);
    }
}
