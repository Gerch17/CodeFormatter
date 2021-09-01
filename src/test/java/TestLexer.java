import exceptions.ReaderException;
import format.analysis.IToken;
import format.analysis.tools.LexerStateMachine;
import io.reader.Reader;
import io.reader.string.StringReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLexer {
    private static final String TEST_STRING = "    a;\nb{}\"\\\"qwe{};\"for(int i = 0; i < qwe.size(); i++)";
    private Reader reader;

    @BeforeEach
    public void setUp() {
        reader = new StringReader(TEST_STRING);
    }

    @Test
    void lexerTest() throws ReaderException {
        LexerStateMachine lexer = new LexerStateMachine(reader);

        IToken spaces =  lexer.nextToken();
        Assertions.assertEquals("    ", spaces.getLexeme());
        Assertions.assertEquals("Spacing", spaces.getName());

        IToken a =  lexer.nextToken();
        Assertions.assertEquals("a", a.getLexeme());
        Assertions.assertEquals("Char", a.getName());

        IToken semicolon =  lexer.nextToken();
        Assertions.assertEquals(";", semicolon.getLexeme());
        Assertions.assertEquals("Semicolon", semicolon.getName());

        IToken newLine =  lexer.nextToken();
        Assertions.assertEquals("\n", newLine.getLexeme());
        Assertions.assertEquals("NewLine", newLine.getName());

        IToken b =  lexer.nextToken();
        Assertions.assertEquals("b", b.getLexeme());
        Assertions.assertEquals("Char", b.getName());

        IToken open =  lexer.nextToken();
        Assertions.assertEquals("{", open.getLexeme());
        Assertions.assertEquals("OpenBracket", open.getName());

        IToken close =  lexer.nextToken();
        Assertions.assertEquals("}", close.getLexeme());
        Assertions.assertEquals("CloseBracket", close.getName());

        IToken quotes =  lexer.nextToken();
        Assertions.assertEquals("\"\\\"qwe{};\"", quotes.getLexeme());
        Assertions.assertEquals("Quotes", quotes.getName());

        lexer.nextToken();
        lexer.nextToken();
        lexer.nextToken();

        IToken brackets =  lexer.nextToken();
        Assertions.assertEquals("(int i = 0; i < qwe.size(); i++)", brackets.getLexeme());
        Assertions.assertEquals("Brackets", brackets.getName());
    }

}
