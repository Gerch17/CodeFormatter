package format.formatter;

import exceptions.WriterException;
import format.analysis.IToken;
import io.writer.Writer;

public class Context implements IContext {
    private Writer writer;
    private int tab;

    public Context(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void writeLexeme(IToken token) throws WriterException {
        for (int i = 0; i < token.getLexeme().length(); i++) {
            writer.writeChar(token.getLexeme().charAt(i));
        }
    }

    @Override
    public void writeNewLine() throws WriterException {
        writer.writeChar('\n');
    }

    @Override
    public void writeIndent() throws WriterException {
        for (int i = 0; i < tab * 4; i++) {
            writer.writeChar(' ');
        }
    }

    @Override
    public void writeSpace() throws WriterException {
        writer.writeChar(' ');
    }

    @Override
    public void incrementTab() {
        tab++;
    }

    @Override
    public void decrementTab() {
        tab--;
    }
}
