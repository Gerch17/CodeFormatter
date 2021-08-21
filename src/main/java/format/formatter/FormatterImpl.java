package format.formatter;

import exceptions.InvalidCodeException;
import format.analysis.Lexer;
import format.analysis.OutPut;
import io.reader.Reader;
import io.writer.Writer;
import java.io.IOException;

public class FormatterImpl implements Formatter {
    @Override
    public String makeItClear(Reader reader, Writer writer) throws IOException, InvalidCodeException {
        OutPut outPut = new OutPut(writer);
        Lexer lexer = new Lexer(reader);
        return outPut.output(lexer.setLexemes()).toString();
    }
}
