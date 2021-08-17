package writer;

import java.io.IOException;

public interface Writer extends AutoCloseable{
    void writeChar(char ch) throws IOException;
}
