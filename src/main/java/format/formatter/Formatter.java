package format.formatter;

import exceptions.InvalidCodeException;
import format.analysis.tools.ILexer;
import io.writer.Writer;
import java.io.IOException;


public interface Formatter {
    String format(ILexer lexer, Writer writer) throws IOException, InvalidCodeException;
}
