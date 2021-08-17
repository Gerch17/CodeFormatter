package reader;

import java.io.IOException;

public interface Reader extends AutoCloseable{
    char readChar() throws IOException;

    boolean hasChar() throws IOException;
}
