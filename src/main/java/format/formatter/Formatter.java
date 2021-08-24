package format.formatter;

import exceptions.InvalidCodeException;
import format.analysis.Lexer;
import io.writer.Writer;
import java.io.IOException;


public interface Formatter {
    String makeItClear(Lexer lexer, Writer writer) throws IOException, InvalidCodeException;
}
