import format.analysis.Lexer;
import format.formatter.FormatterImpl;
import io.reader.string.StringReader;
import io.writer.string.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestFormatterImpl {

    private static final String SIMPLE_STRING = "qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;}}";
    private static final String NEWLINE_STRING = "qweqwewqe\n\n\n\n\n\n\n{qweqwe;qweqwe;if(){qweqwe;qweqwe;}}";
    private static final String TRIM_TEST = "qweqwewqe                     {qweqwe;qweqwe;if(){qweqwe;qweqwe;}}";
    private static final String EXPECTED_SIMPLE_STRING = "qweqwewqe {\n" +
            "    qweqwe;\n" +
            "    qweqwe;\n" +
            "    if() {\n" +
            "        qweqwe;\n" +
            "        qweqwe;\n" +
            "    }\n" +
            "}\n";

    private FormatterImpl formatterImpl;

    @BeforeEach
    public void setUp() {
        this.formatterImpl = new FormatterImpl();
    }

    @Test
    public void simpleTest() throws IOException {
            Lexer lexer = new Lexer(new StringReader(SIMPLE_STRING));
            String actual = formatterImpl.makeItClear(lexer, new StringWriter());
            Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

    @Test
    public void trimTest() throws IOException {
        Lexer lexer = new Lexer(new StringReader(TRIM_TEST));
        String actual = formatterImpl.makeItClear(lexer, new StringWriter());
        Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

    @Test
    public void extraNewLineSeparationTest() throws IOException {
        Lexer lexer = new Lexer(new StringReader(NEWLINE_STRING));
        String actual = formatterImpl.makeItClear(lexer, new StringWriter());
        Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

}
