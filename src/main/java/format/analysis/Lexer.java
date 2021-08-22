package format.analysis;

import exceptions.ReaderException;
import io.reader.Reader;

import java.util.ArrayList;
import java.util.List;


public class Lexer {
    private static final char OPEN_BRACKET = '{';
    private static final char CLOSE_BRACKET = '}';
    private static final char SEMICOLON = ';';
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";
    private final Reader reader;
    private StringBuilder sb;
    private List<Lexeme> lexemes;
    private int tabs = 0;

    public Lexer(Reader reader) {
        this.reader = reader;
        sb = new StringBuilder();
        lexemes = new ArrayList<>();
    }

    public List<Lexeme> setLexemes() throws ReaderException {
        char ch;
        while (reader.hasChar()) {
            ch = reader.readChar();
            if (ch == OPEN_BRACKET) {
                addTextLexeme();
                lexemes.add(new Lexeme(Token.OPEN, tabs));
            } else if (ch == CLOSE_BRACKET) {
                addTextLexeme();
                lexemes.add(new Lexeme(Token.CLOSE, tabs));
            } else if (ch == SEMICOLON) {
                addTextLexeme();
                lexemes.add(new Lexeme(Token.SEMICOLON, tabs));
            } else {
                sb.append(ch);
            }
        }
        makeRightTabs();
        return lexemes;
    }

    private void addTextLexeme() {
        if (sb.length() != 0) {
            String temp = sb.toString().trim().replaceAll("\\s+", SPACE).replaceAll(NEW_LINE, "");
            sb.setLength(0);
            sb.append(temp);
            lexemes.add(new Lexeme(sb.toString(), tabs));
            sb.setLength(0);
        }
    }

    private void makeRightTabs() {
        int actualTabs = 0;
        for (Lexeme lexeme : lexemes) {
            if (lexeme.getType() == Token.OPEN) {
                actualTabs++;
            } else if (lexeme.getType() == Token.CLOSE) {
                actualTabs--;
                lexeme.setTab(actualTabs);
            } else {
                lexeme.setTab(actualTabs);
            }
        }
    }
}
