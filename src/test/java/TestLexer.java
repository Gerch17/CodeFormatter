import exceptions.ReaderException;
import format.analysis.Lexer;
import io.reader.Reader;
import io.reader.string.StringReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestLexer {
    private static final String SIMPLE_TEST = "qweqweqwe";
    private static final String OPEN_BRACKET_TEST = "{";
    private static final String EXPECTED_OPEN_BRACKET_TEST = " {\n";
    private static final String SEMICOLON_TEST = ";";
    private static final String EXPECTED_SEMICOLON_TEST = ";\n";
    private static final String CODE_TEST = "qweqwe;";
    private static final String EXPECTED_CODE_TEST = "qweqwe;\n";
    private static final String FOR_TEST = "for (int i = 0; i < temp.length(); i++){";
    private static final String EXPECTED_FOR_TEST = "for (int i = 0; i < temp.length(); i++) {\n";
    private static final String COMMENT_TEST = "\"q q \\\";{}\"";
    private static final String MAKE_IT_SHORTER_TEST = "qwe{q";
    private static final String EXPECTED_MAKE_IT_SHORTER_TEST = "qwe {\n";

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void lexerTest() throws ReaderException {
        Reader reader = new StringReader(SIMPLE_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(SIMPLE_TEST, lexer.readToken().getLexeme());
    }

    @Test
    public void openBracketTest() throws ReaderException {
        Reader reader = new StringReader(OPEN_BRACKET_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(EXPECTED_OPEN_BRACKET_TEST, lexer.readToken().getLexeme());
    }

    @Test
    public void semiColonTest() throws ReaderException {
        Reader reader = new StringReader(SEMICOLON_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(EXPECTED_SEMICOLON_TEST, lexer.readToken().getLexeme());
    }

    @Test
    public void codeTest() throws ReaderException {
        Reader reader = new StringReader(CODE_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(EXPECTED_CODE_TEST, lexer.readToken().getLexeme());
    }

    @Test
    public void forTest() throws ReaderException {
        Reader reader = new StringReader(FOR_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(EXPECTED_FOR_TEST, lexer.readToken().getLexeme());
    }

    @Test
    public void commentTest() throws ReaderException {
        Reader reader = new StringReader(COMMENT_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(COMMENT_TEST, lexer.readToken().getLexeme());
    }

    @Test
    public void shorterTest() throws ReaderException {
        Reader reader = new StringReader(MAKE_IT_SHORTER_TEST);
        Lexer lexer = new Lexer(reader);
        Assertions.assertEquals(EXPECTED_MAKE_IT_SHORTER_TEST, lexer.readToken().getLexeme());
    }
}
