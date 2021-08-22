package format.analysis;

import exceptions.InvalidCodeException;
import exceptions.WriterException;
import io.writer.Writer;

import java.util.List;

public class OutPut {
    private static final char OPEN_BRACKET = '{';
    private static final char CLOSE_BRACKET = '}';
    private static final char SEMICOLON = ';';
    private static final char NEW_LINE = '\n';
    private static final char SPACE = ' ';
    private static final int TAB = 4;

    private final Writer writer;

    public OutPut(Writer writer) {
        this.writer = writer;
    }

    public Writer output(List<Lexeme> lexemes) throws WriterException {
        simpleCheck(lexemes);
        for (Lexeme lexeme : lexemes) {
            if (lexeme.getType() == Token.OPEN) {
                writer.writeChar(SPACE);
                writer.writeChar(OPEN_BRACKET);
                writer.writeChar(NEW_LINE);
            } else if (lexeme.getType() == Token.SEMICOLON) {
                writer.writeChar(SEMICOLON);
                writer.writeChar(NEW_LINE);
            } else if (lexeme.getType() == Token.CLOSE) {
                writeTab(lexeme.getTab());
                writer.writeChar(CLOSE_BRACKET);
                writer.writeChar(NEW_LINE);
            } else {
                writeTab(lexeme.getTab());
                writeText(lexeme.getValue());
            }
        }
        return writer;
    }

    public void writeTab(int amount) throws WriterException {
        for (int i = 0; i < amount * TAB; i++) {
            writer.writeChar(SPACE);
        }
    }

    public void simpleCheck(List<Lexeme> lexemes) {
        int balance = 0;
        for (Lexeme lexeme : lexemes) {
            if (lexeme.getType() == Token.CLOSE) {
                balance++;
            } else if (lexeme.getType() == Token.OPEN) {
                balance--;
            }
        }
        if (balance != 0) {
            throw new InvalidCodeException("The number of '{' does not match the number of '}'");
        }
    }

    public void writeText(String text) throws WriterException {
        for (int i = 0; i < text.length(); i++) {
            writer.writeChar(text.charAt(i));
        }
    }
}
