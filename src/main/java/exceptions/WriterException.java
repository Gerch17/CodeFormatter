package exceptions;

import java.io.IOException;

public class WriterException extends IOException {
    public WriterException(String message) {
        super(message);
    }
}
