package format.analysis;

import exceptions.WriterException;
import io.writer.Writer;

public class OutPut {
    private static final char OPEN_BRACKET = '{';
    private static final char CLOSE_BRACKET = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SPACE = ' ';
    private static final int TAB = 4;
    private int tabAmount = 0;

    private final Writer writer;

    public OutPut(Writer writer) {
        this.writer = writer;
    }

    public void output(IToken token) throws WriterException {
        if (token.getName().equals(String.valueOf(OPEN_BRACKET))) {
            writer.writeChar(SPACE);
            writer.writeChar(OPEN_BRACKET);
            writer.writeChar(NEW_LINE);
            tabAmount++;
        } else if (token.getName().equals(String.valueOf(SEMICOLON))) {
            writer.writeChar(SEMICOLON);
            writer.writeChar(NEW_LINE);
        } else if (token.getName().equals(String.valueOf(CLOSE_BRACKET))) {
            tabAmount--;
            writeTab();
            writer.writeChar(CLOSE_BRACKET);
            writer.writeChar(NEW_LINE);
        } else {
            writeTab();
            writeText(token.getLexeme());
        }
    }

    public void writeTab() throws WriterException {
        for (int i = 0; i < tabAmount * TAB; i++) {
            writer.writeChar(SPACE);
        }
    }

    public void writeText(String text) throws WriterException {
        for (int i = 0; i < text.length(); i++) {
            writer.writeChar(text.charAt(i));
        }
    }
}
