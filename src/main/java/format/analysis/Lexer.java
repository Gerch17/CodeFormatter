package format.analysis;

import exceptions.ReaderException;
import io.reader.Reader;

public class Lexer implements ILexer {
    private static final String TEXT = "TEXT";
    private static final char OPEN_BRACKET = '{';
    private static final char CLOSE_BRACKET = '}';
    private static final char SEMICOLON = ';';
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private final Reader reader;
    private StringBuilder sb;
    private int tabs = 0;
    private static char nextToken = '\0';

    public Lexer(Reader reader) {
        this.reader = reader;
        sb = new StringBuilder();
    }

    @Override
    public IToken readToken() throws ReaderException {
        char ch;
        Token token;
        if (nextToken != '\0') {
            IToken newToken = makeToken(nextToken);
            nextToken = '\0';
            return newToken;
        }
        ch = reader.readChar();
        if (ch == OPEN_BRACKET || nextToken == OPEN_BRACKET) {
            token = new Token(String.valueOf(OPEN_BRACKET), String.valueOf(OPEN_BRACKET));
            nextToken = '\0';
        } else if (ch == CLOSE_BRACKET || nextToken == CLOSE_BRACKET) {
            token = new Token(String.valueOf(CLOSE_BRACKET), String.valueOf(CLOSE_BRACKET));
            nextToken = '\0';
        } else if (ch == SEMICOLON || nextToken == SEMICOLON) {
            token = new Token(String.valueOf(SEMICOLON), String.valueOf(SEMICOLON));
            nextToken = '\0';
        } else {
            String text = String.valueOf(ch);
            char temp = reader.hasChar() ? reader.readChar() : '\0';
            while (isText(temp)) {
                text += temp;
                temp = reader.hasChar() ? reader.readChar() : '\0';
            }
            token = new Token(TEXT, text.trim().replaceAll("\\s+", SPACE).replaceAll(NEW_LINE, ""));
        }
        return token;
    }

    private boolean isText(char ch) {
        if (ch != OPEN_BRACKET && ch != CLOSE_BRACKET && ch != SEMICOLON) {
            return true;
        }
        nextToken = ch;
        return false;
    }

    private IToken makeToken(char ch) {
        IToken token = new Token(String.valueOf(ch), String.valueOf(ch));
        return token;
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        if (reader.hasChar() || nextToken != '\0') {
            return true;
        }
        return false;
    }
}
