package format.formatter;

import exceptions.InvalidCodeException;
import format.analysis.Lexer;
import format.analysis.OutPut;
import io.writer.Writer;
import java.io.IOException;

public class FormatterImpl implements Formatter {
    @Override
    public String makeItClear(Lexer lexer, Writer writer) throws IOException, InvalidCodeException {
        OutPut outPut = new OutPut(writer);
        while (lexer.hasMoreTokens()) {
            outPut.output(lexer.readToken());
        }
        return writer.toString();
    }
}
