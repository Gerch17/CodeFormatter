package format.formatter;

import exceptions.InvalidCodeException;
import io.reader.Reader;
import io.writer.Writer;
import java.io.IOException;


public interface Formatter {
    String makeItClear(Reader reader, Writer writer) throws IOException, InvalidCodeException;
}
